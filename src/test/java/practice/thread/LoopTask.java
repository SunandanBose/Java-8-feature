package practice.thread;

import java.util.concurrent.TimeUnit;

public class LoopTask implements Runnable {


    static int count;
    int id = 0;

    @Override
    public void run() {

//        Thread.currentThread().setName("LoopTask"+id);

        String threadName = Thread.currentThread().getName();


        System.out.println("*********[" + threadName + "] Task ID " + id + " Execute Task : start");

        try {
            for (int i = 0; i < 10; i++) {

                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));

                System.out.println("Task ID " + id + " TICK TICK " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("#########[" + threadName + "]Task ID " + id + " Execute Task : end");
    }

    LoopTask() {
        id = ++count;
    }
}
