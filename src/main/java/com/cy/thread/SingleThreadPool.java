package com.cy.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task3 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

//SingleThreadExecutor（单线程池）
public class SingleThreadPool {
    public static void main(String[] args) {
        //顾名思义，只有一个线程的线程池
        ExecutorService exe  = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exe.execute(new Task3());
        }
    }
}
