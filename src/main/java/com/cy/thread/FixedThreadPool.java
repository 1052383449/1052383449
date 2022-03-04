package com.cy.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

//FixedThreadPool（定长线程池）
public class FixedThreadPool {
    public static void main(String[] args) {
        //创建一个定长的线程池。每提交一个任务，则创建一个工作线程，达到最大线程数目后，进入排序队列。
        //该线程池不会释放空闲线程，对资源有一定的占用和浪费。
        ExecutorService exe = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++){
            exe.execute(new Task2());
        }

        exe.shutdown();
    }
}
