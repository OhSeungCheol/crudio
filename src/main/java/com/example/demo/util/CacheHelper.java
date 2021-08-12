package com.example.demo.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheHelper {
    private final CacheManager cacheManager;

    public Object getCacheKey(Object ...params){
        return new ArrayList<>(Arrays.asList(params));
    }

    public Object getData(String storeName, Object key) {
        Cache cache = cacheManager.getCache(storeName);
        final Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper != null) {
            return valueWrapper.get();
        }
        return null;
    }

    public void putData(String storeName, Object key, Object data) {
        final Cache cache = cacheManager.getCache(storeName);
        cache.put(key, data);
    }

    public Object processCache(String storeName, Object key, Supplier supplier) {
        final Object cacheData = this.getData(storeName, key);
        if (nonNull(cacheData)) {
            return cacheData;
        }
        final Object o = supplier.get();
        this.putData(storeName, key, o);
        return o;
    }
}
