package com.cy.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("file:E:/idea2/java_test/src/main/java/com/cy/spring/bean/tx.xml");
        A a = (A)ac.getBean("aFactoryBean");
        A a2 = (A)ac.getBean("aFactoryBean");
        System.out.println(a.toString());
    }
}
