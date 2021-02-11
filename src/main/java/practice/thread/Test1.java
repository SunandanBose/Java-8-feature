package practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

// make two threads print values A and B consecutively

public class Test1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new NameThread());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Test());
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

}

class Test implements Runnable {

    @Override
    public void run() {

        try {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class NameThread implements ThreadFactory {

    private static int ch = 'A';

    @Override
    public Thread newThread(Runnable r) {
        char name = (char) (ch++);
        Thread t = new Thread(r, Character.toString(name));

        return t;
    }

}
