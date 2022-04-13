package com.wangsq.tools;

/**
 * @author 福泰
 * @version WrapThreadUtils.java, v 0.1 2022年04月13日 11:44 AM 福泰
 */

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class WrapThreadUtils {

    /**
     * 执行线程
     *
     * @param completionStages
     * @param <T>
     * @return
     */
    public static <T> CompletionStage<List<T>> batchExecuteThreads(List<CompletionStage<T>> completionStages) {
        CompletableFuture<List<T>> result = ThreadUtils.supply(() -> Lists.newCopyOnWriteArrayList());
        for (CompletionStage<T> stage : completionStages) {
            result = result.thenCombine(stage, (list, item) -> {
                if (item != null) {
                    list.add(item);
                }
                return list;
            });
        }
        return result;
    }

}
