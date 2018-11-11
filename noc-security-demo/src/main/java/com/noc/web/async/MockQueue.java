package com.noc.web.async;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 虚拟队列
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());
    // 代表下单消息
    private String placeOrder;
    // 代表订单完成消息
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {
        // 模拟应用2的线程
        new Thread(() -> {
            logger.info("接到下单请求, " + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            logger.info("下单请求处理完毕," + placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }

}
