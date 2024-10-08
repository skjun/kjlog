package com.skjun.springboot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author shenkaiwen5
 * @version 1.0
 * @date 2021-12-27
 */
@Component
@RestController
public class TestController {


    /**
     * do nothing
     * just as an adapter for this project common log helper
     *
     */
    private static Logger logger = LoggerFactory.getLogger("TestController");

    @RequestMapping("/index")
    public Object index()  {
        return "index";
    }

    @RequestMapping("/log")
    public Object log() {
        logger.info("|errno=val3||node=val4||这是随便的log|");
        return 1;
    }


    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object test() {
//        for (int i = 0; i < 3; i++) {
//            logger.info("----------------"+i);
//            new Thread(this::startServer).start();
//            logger.info("+++++++++++++++++++++"+i);
//        }
        new Thread(this::startServer).start();
        logger.info("|errno=val3||node=val4||这是随便的log|");
        return "滴滴员工tangbohu的终身代号是什么？？？是9527";
    }
    public void startServer() {
        for (int i = 0; i < 3000; i++) {
            logger.info("test log "+i);
        }
    }



    @PostMapping(value = "/exception", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object exception() {
        logger.info("exception----------");
        throw new RuntimeException("发生异常了");

    }

}
