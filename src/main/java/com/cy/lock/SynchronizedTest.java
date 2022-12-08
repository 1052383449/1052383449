package com.cy.lock;

public class SynchronizedTest {
    String eml;
    //修饰方法
    public synchronized  void method(){
        //修饰代码块
        synchronized(this){
            //todo
        }
        //修饰代码块
        synchronized(eml){
            //todo
        }
    }
}
