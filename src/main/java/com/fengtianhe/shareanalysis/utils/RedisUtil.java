package com.fengtianhe.shareanalysis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate template;

    public List<String> getAllKeys(String pattern) {
        Set<String> keySet = template.keys(pattern);
        List<String> keys = new ArrayList<String>();
        for (String key : keySet) {
            keys.add(key);
        }
        return keys;

    }

    public void set(String key, String value) {
        template.opsForValue().set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit unit) {
        template.opsForValue().set(key, value, timeout, unit);
    }

    public String get(String key) {
        return template.opsForValue().get(key);
    }

    public boolean notExist(String key) {
        return !exist(key);
    }

    public boolean exist(String key) {
        return template.hasKey(key);
    }

    public boolean hexists(String key, String field) {
        return template.opsForHash().hasKey(key, field);
    }

    public boolean notHexists(String key, String field) {
        return !hexists(key, field);
    }

    public <T> void hset(String key, String field, T value) {
        template.opsForHash().put(key, field, value.toString());
    }

    public <T> void hset(String key, String field, String value) {
        template.opsForHash().put(key, field, value);
    }

    public long hdel(String key, Object... fields) {
        return template.opsForHash().delete(key, fields);
    }

    /**
     * 底层序列化限制只能用字符串
     *
     * @param key
     * @param
     */
    public void hMSet(String key, Map<String, String> m) {
        template.opsForHash().putAll(key, m);
    }

    @SuppressWarnings("unchecked")
    public <T> T hget(String key, String field) {
        return (T) template.opsForHash().get(key, field);
    }

    public Map<Object, Object> hgetall(String key) {
        return template.opsForHash().entries(key);
    }

    public Long hincrement(String key, String field, long delta) {
        return template.opsForHash().increment(key, field, delta);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return template.expire(key, timeout, unit);
    }

    public void del(String key) {
        template.delete(key);
    }

    public void lpush(String key, String value) {
        template.opsForList().rightPush(key, value);
    }

    public long incr(String key) {
        return template.boundValueOps(key).increment(1);
    }

    public long decr(String key) {
        return template.boundValueOps(key).increment(-1);
    }

    public boolean setNX(String key, String value) {
        Boolean s = template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = template.getStringSerializer();
                byte[] keyByte = serializer.serialize(key);
                byte[] valueByte = serializer.serialize(value);
                return connection.setNX(keyByte, valueByte);
            }
        });
        if (s == null) {
            return false;
        } else {
            return s;
        }
    }

    public void setEX(String key, String value , long expireTime) {
        template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = template.getStringSerializer();
                byte[] keyByte = serializer.serialize(key);
                byte[] valueByte = serializer.serialize(value);
                connection.setEx(keyByte,expireTime,valueByte);
                return true;
            }
        });
    }

    public Set<String> keys(String pattern) {
        return template.keys(pattern);
    }
}
