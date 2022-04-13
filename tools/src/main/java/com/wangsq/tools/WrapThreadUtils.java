package com.wangsq.tools;

/**
 * @author 福泰
 * @version WrapThreadUtils.java, v 0.1 2022年04月13日 11:44 AM 福泰
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 *
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author futai
 * @version : ThreadUtilsWrap, v 0.1 2021-11-13 上午12:41 futai Exp $
 */
public class WrapThreadUtils {

    /**
     * 执行线程
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
