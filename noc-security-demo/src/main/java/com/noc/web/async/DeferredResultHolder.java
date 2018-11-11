package com.noc.web.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 在线程1和线程2之间传递DeferredResult对象
 */
@Data
@Component
public class DeferredResultHolder {

    // key：订单号
    // value：订单处理结果
    private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();

}
