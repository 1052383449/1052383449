package cy.thread;

import java.util.HashMap;
import java.util.Map;

public class MyRunnable {
    public static ThreadLocal<Map<String,Object>> threadLocal =new ThreadLocal<>();

    public static Map<String,Object> tmap = new HashMap<>();

    public static Integer index = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try{
                        synchronized(index){
                            index++;
                            System.out.println(Thread.currentThread().getName()+","+index);
                            Thread.yield();
                        }

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try{
                        synchronized(index){
                            index++;
                            System.out.println(Thread.currentThread().getName()+","+index);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
        t.join();
        t2.join();

        System.out.println(index);
    }
}
