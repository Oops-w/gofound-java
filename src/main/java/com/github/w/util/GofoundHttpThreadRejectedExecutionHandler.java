package com.github.w.util;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wsy
 * @date 2022/11/5 2:57 PM
 * @Description
 */
public class GofoundHttpThreadRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        throw new RuntimeException("current thread pool have to many task please late try again");
    }
}
