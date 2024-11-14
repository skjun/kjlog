package com.skjun.log.agent.upload.udp;

import com.skjun.log.agent.core.config.LogConfig;
import com.skjun.log.agent.core.handler.AbstractUpHandler;
import com.skjun.log.agent.core.handler.node.Node;
import com.skjun.log.agent.core.handler.node.SmoothWeightedRoundRobin;
import com.skjun.log.agent.core.util.AsyncPool;
import com.skjun.log.agent.core.util.ProtostuffUtils;
import com.skjun.log.agent.core.util.ZstdUtils;
import com.skjun.log.server.lib.dto.TraceUpData;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.logging.Logger;

public class AbstractUpUdpHandler extends AbstractUpHandler {

    private static final  Logger logger = Logger.getLogger(AbstractUpUdpHandler.class.getName());
    private LogConfig logConfig;

    private Channel channel;


    private  SmoothWeightedRoundRobin smoothWeightedRoundRobin;

    @Override
    public void init(LogConfig logConfig) {

        smoothWeightedRoundRobin = new SmoothWeightedRoundRobin();
        this.logConfig = logConfig;

        logConfig.getUdpServers().forEach(server->{
            String[] split = server.split(":");
            if(split.length == 2){
                smoothWeightedRoundRobin.addNode(new Node(new InetSocketAddress(split[0],Integer.parseInt(split[1]))));
            }
        });
        AsyncPool.asyncDo(this::startUdp);
    }


    /**
     * startUdp
     */
    private void startUdp() {
        //1.NioEventLoopGroup是执行者
        NioEventLoopGroup group = new NioEventLoopGroup();
        //2.启动器
        Bootstrap bootstrap = new Bootstrap();
        //3.配置启动器
        bootstrap.group(group)                         //3.1指定group
                .channel(NioDatagramChannel.class)     //3.2指定channel
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel nioDatagramChannel) {
                        //3.4在pipeline中加入编码器，和解码器（用来处理返回的消息）
                        nioDatagramChannel.pipeline().addLast(new UdpEncoder());
                    }
                });
        //4.bind并返回一个channel
        try {
            channel = bootstrap.bind(logConfig.getLocalUdpPort()).sync().channel();
            //6.等待channel的close
            channel.closeFuture().sync();
            //7.关闭group
            group.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编码器，将要发送的消息（这里是一个String）封装到一个DatagramPacket中
     */
    private static class UdpEncoder extends MessageToMessageEncoder<TraceUpData> {
        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext, TraceUpData tracerData, List<Object> list) {
            byte[] bytes = ProtostuffUtils.serialize(tracerData);

            byte[] compressBytes = ZstdUtils.compress(bytes);
//            //判断压缩完是否过大，过大走http接口请求worker
//            if (compressBytes.length >= COMPRESS_BYTES_LEN) {
//                //放入发okhttp的队列
//                HttpSender.offerBean(compressBytes,tracerData.getAddress());
//                return;
//            }
            ByteBuf buf = channelHandlerContext.alloc().buffer(compressBytes.length);
            buf.writeBytes(compressBytes);
            InetSocketAddress  remoteAddress=tracerData.getAddress();
            DatagramPacket packet = new DatagramPacket(buf, remoteAddress);
            list.add(packet);
        }
    }

    @Override
    public void sendData(TraceUpData data) throws InterruptedException {
        data.setAddress((InetSocketAddress) smoothWeightedRoundRobin.select().getTarget());
        if(channel != null){
            ChannelFuture future = channel.writeAndFlush(data);
            //同步操作，否则会出现bug
            future.sync();
        }

    }
}
