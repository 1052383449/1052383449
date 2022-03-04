package com.cy.history.collection;

import java.util.*;

public class list_test {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<Integer>();
        //初始容量测试,jdk1.8以前为10,1.8以后为0
        //添加元素时进行扩容，默认为10
        arrayList.add(1);
        for (int i=0;i<100;i++){
            //每次扩容都是当前容量的1.5倍
            arrayList.add(i);
        }

        //可预知list大小的时候要指定容量大小
        //验证clone和toArray方法,都是浅拷贝
        ArrayList<Map<String,String>> mapList = new ArrayList<Map<String,String>>(5);
        Map<String,String> map = new HashMap<String,String>();
        map.put("ss","33");
        mapList.add(map);

        ArrayList<Map<String,String>> mapList1 = (ArrayList<Map<String,String>>)mapList.clone();
        Map<String,String>[] maps = new Map[1];
        Map<String,String>[] mapList2 =  mapList.toArray(maps);

        map.put("ss","22");
        System.out.println(mapList1.get(0).toString());
        System.out.println(mapList2[0].toString());

        //remove和set方法测试
        arrayList.remove(10);

        List<Integer> linkedList = new LinkedList<Integer>();



    }
}
