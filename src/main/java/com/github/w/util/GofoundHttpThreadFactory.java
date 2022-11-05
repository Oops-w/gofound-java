package com.github.w.util;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wsy
 * @date 2022/11/5 2:55 PM
 * @Description
 */
public class GofoundHttpThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(0);
    @Override
    public Thread newThread(@NotNull Runnable r) {
        return new Thread(r, "gofound-client-" + threadNumber.getAndIncrement());
    }
}
