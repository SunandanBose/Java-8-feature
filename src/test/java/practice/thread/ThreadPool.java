package practice.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public static void main(String[] args) {


        System.out.println(Thread.currentThread());
        String threadName = Thread.currentThread().getName();

        System.out.println("[" + threadName + "] Main method start ");

        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadNaming());

        for (int i = 0; i < 6; i++) {
            executorService.execute(new LoopTask());
        }

        executorService.shutdown();

        System.out.println("[" + threadName + "] Main method end ");
    }
}
