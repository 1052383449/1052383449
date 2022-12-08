package com.cy.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    ReentrantLock lock = new ReentrantLock();
    public void method() throws InterruptedException {
        //获取锁，成功返回true
        if(lock.tryLock()){
            try{
                //todo 业务代码
            }finally{
                //释放锁
                lock.unlock();
            }
        }
        //获取锁，两秒之内获取不到，就会放弃等待。
        //if(lock.tryLock(2, TimeUnit.SECONDS))
    }
}
