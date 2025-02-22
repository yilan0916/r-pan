package com.yilan.pan.cache.caffeine.test.instance;

import com.yilan.pan.cache.core.constants.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Cache注解测试实体
 *
 * @author yilan0916
 * @date 2024/7/27
 */
@Component
@Slf4j
public class CacheAnnotationTester {

    /**
     * 测试自适应缓存注解
     *
     * @param name
     * @return
     */
    @Cacheable(cacheNames = CacheConstants.R_PAN_CACHE_NAME, key = "#name", sync = true)
    public String testCacheable(String name) {
        log.info("call com.imooc.pan.cache.caffeine.test.instance.CacheAnnotationTester.testCacheable, param is {}", name);
        return new StringBuilder("hello ").append(name).toString();
    }
}
