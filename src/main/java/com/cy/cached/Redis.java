package com.cy.cached;

import com.alibaba.fastjson.JSONObject;
import com.cy.cached.entity.Friend;
import com.cy.cached.entity.Mood;
import com.cy.cached.entity.User;
import com.cy.utils.JavaBeanUtils;
import org.springframework.beans.BeanUtils;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Redis {
    public static Jedis jedis = new Jedis("localhost");
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        //连接本地的 Redis 服务

        System.out.println("连接成功");

        //微信朋友圈缓存设计
        //给用户信息赋值
        setMoodInfo();
        //获取张三的信息
        System.out.print("获取张三的信息:");
        List<String> user1 = jedis.hvals("1111");
        System.out.println(user1.toString());


        //获取张三的朋友信息
        System.out.print("获取张三的朋友信息:");
        List<String> list = jedis.lrange( "1111_friend", 0, -1 );
        System.out.println(list.toString());

        //获取张三发的朋友圈信息
        System.out.print("获取张三发的朋友圈信息:");
        jedis.del("1111_mood_gather");
        Set<String> zrange = jedis.zrange("1111_mood", 0, -1);
        System.out.println(zrange.toString());

        getMoodInfo("1114");

    }

    public static void setMoodInfo() throws ParseException {
        //给用户信息赋值
        User user1 = new User("1111","张三","男");
        Map<String, String> hash1 = (HashMap) JavaBeanUtils.getInstance().javaBeanToMap(user1);
        jedis.hmset(user1.getId(),hash1);
        User user2 = new User("1112","李四","男");
        Map<String, String> hash2 = (HashMap) JavaBeanUtils.getInstance().javaBeanToMap(user2);
        jedis.hmset(user2.getId(),hash2);
        User user3 = new User("1113","晶晶","男");
        Map<String, String> hash3  = (HashMap) JavaBeanUtils.getInstance().javaBeanToMap(user3);
        jedis.hmset(user3.getId(),hash3);
        User user4 = new User("1114","兰兰","男");
        Map<String, String> hash4 =  (HashMap) JavaBeanUtils.getInstance().javaBeanToMap(user4);
        jedis.hmset(user4.getId(),hash4);

        //给张三添加朋友：李四和晶晶
        //删除区间之外的元素
        jedis.del("1111_friend");
        jedis.lpush("1111_friend","1112","1114");
        //给兰兰添加朋友：李四和晶晶
        jedis.del("1114_friend");
        jedis.lpush("1114_friend","1112","1113");

        //给张三添加朋友圈信息
        jedis.del("1111_mood");
        Mood mood3=new Mood("0001","1111","兰兰也是个好女孩啊，她有很多小钱钱！2021-03-05",sdf.parse("2021-03-05").getTime());
        jedis.zadd("1111_mood",mood3.getDateTime(), JSONObject.toJSONString(mood3));
        Mood mood1=new Mood("0002","1111","今天天气真好，2020-10-10",sdf.parse("2020-10-10").getTime());
        jedis.zadd("1111_mood",mood1.getDateTime(), JSONObject.toJSONString(mood1));
        Mood mood2=new Mood("0003","1111","晶晶今天好漂亮啊，穿了一条蓝色的碎花裙！2020-12-22",sdf.parse("2020-12-22").getTime());
        jedis.zadd("1111_mood",mood2.getDateTime(), JSONObject.toJSONString(mood2));



        //给李四添加朋友圈信息
        jedis.del("1112_mood");
        Mood mood4=new Mood("0004","1112","今天认识了一个讨厌的人，他叫张三！2019-12-21",sdf.parse("2019-12-21").getTime());
        jedis.zadd("1112_mood",mood4.getDateTime(), JSONObject.toJSONString(mood4));

        //给兰兰添加朋友圈信息
        jedis.del("1114_mood");
        Mood mood5=new Mood("0005","1114","今天是情人节呢，不知道又会收到多少礼物...2022-07-07",sdf.parse("2022-07-07").getTime());
        jedis.zadd("1114_mood",mood5.getDateTime(), JSONObject.toJSONString(mood5));

    }


    public static void getMoodInfo(String userId){
        //获取该用户的朋友圈集合信息
        Set<String> zrange = jedis.zrange(userId+"_mood_gather", 0, 20);
        if(zrange.size()==0){
            //获取张三的朋友信息
            List<String> list = jedis.lrange( userId+"_friend", 0, -1 );
            String[] sets =new String[list.size()+1];
            for (int i = 0; i < list.size(); i++) {
                sets[i]=list.get(i)+"_mood";
            }
            sets[sets.length-1] = userId+"_mood";
            Long mood_gather_length=jedis.zunionstore(userId+"_mood_gather",sets);
            System.out.println("一共有"+mood_gather_length+"条朋友圈信息");

            zrange = jedis.zrange(userId+"_mood_gather", 0, -1);

        }
        for (String s : zrange) {
            JSONObject jsonObject=JSONObject.parseObject(s);
            Mood stu=(Mood)JSONObject.toJavaObject(jsonObject, Mood.class);
            System.out.println(stu.getContent());
        }
    }
}
