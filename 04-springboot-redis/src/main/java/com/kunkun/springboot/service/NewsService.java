package com.kunkun.springboot.service;

import com.kunkun.springboot.dao.NewsDAO;
import com.kunkun.springboot.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsDAO newsDAO;

    //springboot自动初始化，不需要我们进行配置，直接注入到代码中使用
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    //第一种方式：方法加锁
    public /*synchronized*/ List<News> getLatestNews(int userId,int offset,int limit){

        //设置序列化方式，防止乱码
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //第一步：查询缓存
        News news= (News) redisTemplate.opsForValue().get("newsKey");
        //判断是否存在缓存
        if(null == news){

            //第二种方式：双重检测锁
            synchronized (this){
                //查询数据库
                news = newsDAO.selectByUserIdAndOffset(userId,offset,limit).get(0);
                //
                redisTemplate.opsForValue().set("newsKey",news);

                System.out.println("进入数据库。。。。。。。。");
            }

        }else{
            System.out.println("进入缓存。。。。。。。。。");
        }


        return newsDAO.selectByUserIdAndOffset(userId,offset,limit);

    }
}
