package com.wangsq.tools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

/**
 * @author 福泰
 * @version ThreadUtlis.java, v 0.1 2022年04月13日 11:46 AM 福泰
 */
public class ThreadUtils {

    private static ExecutorService executor;

    public static <U> CompletableFuture<U> supply(Supplier<U> supplier) {
        CompletableFuture<U> d = new CompletableFuture<U>();

        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    d.complete(supplier.get());
                } catch (Throwable e) {
                    d.completeExceptionally(e);
                } finally {
                }
            }
        });
        return d;
    }

    public void setExecutor(ExecutorService executor) {
        ThreadUtils.executor = executor;
    }

}
