package cy.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Task4 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

//（定时器线程池）
public class ScheduledThreadPool {
    public static void main(String[] args) {
        //创建定长的线程池，支持定时以及周期性的任务执行。
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(3);
        exe.scheduleAtFixedRate(new Task4(),1,3, TimeUnit.SECONDS);
    }
}
