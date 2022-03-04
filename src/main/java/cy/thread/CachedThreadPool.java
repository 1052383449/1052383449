package cy.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Task implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

//弹性缓存线程池
public class CachedThreadPool {
    public static void main(String[] args) {
        // 创建之初，线程池中没有一个线程，当通过 execute()和 submit()方法提交任务时，如果有空的线程，则用空的线程执行该任务，
        // 否则，创建新的线程来执行该任务。创建线程的最大值，取决于最大默认值。默认超过60s空闲，线程就会被回收。
        ExecutorService exe = Executors.newCachedThreadPool();
        for (int i = 0; i<5; i++){
            exe.execute(new Task());
            try{
                /*Thread.sleep(1000);*/
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        exe.shutdown();
    }
}
