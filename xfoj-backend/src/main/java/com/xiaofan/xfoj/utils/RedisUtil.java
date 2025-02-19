package com.xiaofan.xfoj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public boolean del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                return redisTemplate.delete(key[0]);
            }
            return redisTemplate.delete(CollectionUtils.arrayToList(key)) > 0 ? true : false;
        }
        return false;
    }

    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取key的过期时间，单位：秒
     * @param key   key
     * @return  过期时间
     */
    public long getExpireSeconds(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 指定key进行自增number
     * @param key  redis中的kye
     * @param number 如果需要传入整数，可以使用new Double();
     * @return  返回自增后的结果，类型为Double
     */
    public Long incr(String key, int number){
        if(number < 0){
            new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,number);
    }


    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @return
     */
    public Boolean expire(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

}
