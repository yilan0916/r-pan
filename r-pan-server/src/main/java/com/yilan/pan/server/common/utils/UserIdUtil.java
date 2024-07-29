package com.yilan.pan.server.common.utils;

import com.yilan.pan.core.constants.RPanConstants;

import java.util.Objects;

/**
 * 用户ID存储工具类
 *
 * @author yilan0916
 * @date 2024/7/28
 */
public class UserIdUtil {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的用户ID
     *
     * @param userId
     */
    public static void set(Long userId) {
        threadLocal.set(userId);
    }

    /**
     * 获取当前线程的用户ID
     *
     * @return
     */
    public static Long get() {
        Long userId = threadLocal.get();
        if (Objects.isNull(userId)) {
            return RPanConstants.ZERO_LONG;
        }
        return userId;
    }
}
