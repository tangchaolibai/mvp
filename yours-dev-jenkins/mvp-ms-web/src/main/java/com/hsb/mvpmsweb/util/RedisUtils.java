package com.hsb.mvpmsweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RedisUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	  *  普通缓存获取
     *
     * @param key 键
     * @return value 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    
    /**
          *  普通缓存放入
     *
     * @param key 键
     * @param value 值
     * @return true成功/false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
        	logger.error(key, e);
            return false;
        }
    }
    
    /**
          *  删除缓存
     *
     * @param key 可以传一个或多个值 
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

}
