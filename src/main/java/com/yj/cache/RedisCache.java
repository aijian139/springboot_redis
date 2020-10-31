package com.yj.cache;

import com.yj.utils.ApplicationContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// 实现 mybatis 自带的缓存 需要一个 String id 属性 和一个带有id 的构造方法
public class RedisCache implements Cache {

    private final String id;

//    @Autowired
//    private RedisTemplate redisTemplate;


    public RedisCache(String id){
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
       // RedisTemplate redisTemplate = this.redisTemplate();
        // id : com.yj.dao
        // key : -1676024827:3481191117:com.yj.dao.UserDao.findAll:0:2147483647:select * from user:SqlSessionFactoryBean
        // value : [User(id=1, name=yijian, address=湖南长沙)]
        redisTemplate().opsForHash().put(id.toString(), key.toString(),value);
        System.out.println("id:"+id);
        System.out.println("putObject:"+key+"-----------"+value);
    }

    @Override
    public Object getObject(Object key) {
        System.out.println(key);
        RedisTemplate redisTemplate = this.redisTemplate();

        return redisTemplate.opsForHash().get(id.toString(),key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = this.redisTemplate();
        redisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        RedisTemplate redisTemplate = this.redisTemplate();

        return redisTemplate.opsForHash().size(id).intValue();
    }

    private RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate)ApplicationContextUtil.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
