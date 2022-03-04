package com.cy.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long time = System.currentTimeMillis();

        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try{
                    Thread.sleep(3000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                return "测试结果";
            }
        });
        futureTask.run();
        FutureTask futureTask2 = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try{
                    Thread.sleep(3000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                return "测试结果2";
            }
        });
        futureTask2.run();
        Thread.sleep(3000);
        System.out.println(futureTask.get().toString());
        System.out.println(futureTask2.get().toString());

        System.out.println("222222");
        System.out.println(System.currentTimeMillis()-time);
    }
}
