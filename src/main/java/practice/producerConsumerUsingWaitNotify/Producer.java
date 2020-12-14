package practice.producerConsumerUsingWaitNotify;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Producer implements Runnable {

    private Queue<Integer> queue;
    private int maxSize;

    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 50) {
            //synchronized block, internally Java uses a monitor also known as monitor lock or intrinsic lock, to provide synchronization.
            //These monitors are bound to an object, thus all synchronized blocks of the same object can have only one thread executing them at the same time.
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        System.out.println("Queue is Full");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Producer value : " + count);
                queue.add(count++);
//                notifyAll() wakes up all threads that are waiting on this object's monitor. A thread waits on an object's
//                monitor by calling one of the wait methods. The awakened threads will not be able to proceed until the current
//                thread relinquishes the lock on this object.
                queue.notifyAll();

                String s = "";
                s.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.summarizingInt(c -> 1)));
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