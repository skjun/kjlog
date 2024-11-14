package com.skjun.log.server.receive.udp;

import com.lmax.disruptor.RingBuffer;
import com.skjun.log.server.core.disruptor.LogEvent;
import com.skjun.log.server.core.inte.IServerReceiveHandler;
import com.skjun.log.server.core.util.ProtostuffUtils;
import com.skjun.log.server.core.util.ZstdUtils;
import com.skjun.log.server.lib.dto.TraceUpData;
import com.skjun.log.server.receive.udp.config.UdpConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UdpServiceReceivImpl implements IServerReceiveHandler {
    private final Logger logger = Logger.getLogger(UdpServiceReceivImpl.class.getName());


    @Autowired
    private RingBuffer<LogEvent> disruptor;

    @Autowired
    private UdpConfig udpConfig;

    @Override
    public void init() {
        //开启服务监听
        new Thread(this::startServer).start();
    }

    /**
     * 启动server监听器
     */
    public void startServer() {

        logger.info("udp receive Start ! port: "+ udpConfig.getPort());

        //1.NioEventLoopGroup是执行者
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        //2.启动器
        Bootstrap bootstrap = new Bootstrap();
        //3.配置启动器
        bootstrap.group(group)//3.1指定group
                .channel(NioDatagramChannel.class)//3.2指定channel
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel nioDatagramChannel) {
                        //3.4在pipeline中加入解码器，和编码器（用来发送UDP）
                        nioDatagramChannel.pipeline().addLast(new LogDecoder());
                    }
                });
        try {
            //4.bind到指定端口，并返回一个channel，该端口就是监听UDP报文的端口
            Channel channel = bootstrap.bind(udpConfig.getPort()).sync().channel();
            //5.等待channel的close
            channel.closeFuture().sync();
            //6.关闭group
            group.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码器，解析客户端发来的对象
     */
    private class LogDecoder extends MessageToMessageDecoder<DatagramPacket> {
        @Override
        protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) {
            ByteBuf buf = datagramPacket.content();
            byte[] body = new byte[buf.readableBytes()];
            buf.readBytes(body);

            //压缩后的字节数组
            byte[] decompressBytes = ZstdUtils.decompressBytes(body);

            TraceUpData tracerData = ProtostuffUtils.deserialize(decompressBytes, TraceUpData.class);

            long sequence = disruptor.next();
            try {
                LogEvent order = disruptor.get(sequence);
                order.setTraceUpData(tracerData);
            } finally {
                disruptor.publish(sequence);
            }
        }
    }

}
