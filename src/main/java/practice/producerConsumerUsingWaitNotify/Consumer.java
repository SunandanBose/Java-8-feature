package practice.producerConsumerUsingWaitNotify;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Consumer implements Runnable {

    private Queue<Integer> queue;
    private int maxSize;

    public Consumer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        System.out.println("Queue is Empty");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consumer value : " + queue.remove());
//                notifyAll() wakes up all threads that are waiting on this object's monitor. A thread waits on an object's
//                monitor by calling one of the wait methods. The awakened threads will not be able to proceed until the current
//                thread relinquishes the lock on this object.
                queue.notifyAll();
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
