package com.noc.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 同步
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/main")
    public String asyncMain() throws Exception {
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return "success";
    }

    /**
     * 使用Runnable异步处理Rest服务
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/callable")
    public Callable<String> asyncCallable() throws Exception {
        logger.info("主线程开始");
        Callable<String> result = () -> {
            logger.info("副线程开始");
            Thread.sleep(1000);
            logger.info("副线程返回");
            return "success";
        };
        logger.info("主线程返回");
        return result;
    }

    /**
     * 使用DeferredResult异步处理Rest服务
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/deferredResult")
    public DeferredResult<String> asyncDeferredResult() throws Exception {
        logger.info("主线程开始");
        // 生成随机订单号
        String orderNumber = RandomStringUtils.randomNumeric(8);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        // 放入消息队列
        mockQueue.setPlaceOrder(orderNumber);
        logger.info("主线程返回");
        return result;
    }

}
