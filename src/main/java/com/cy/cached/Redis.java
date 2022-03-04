package com.cy.cached;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

public class Redis {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

        Long value = jedis.incr("jpbq");
        System.out.println(value);
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }
}
