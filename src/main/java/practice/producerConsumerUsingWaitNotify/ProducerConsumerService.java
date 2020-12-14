package practice.producerConsumerUsingWaitNotify;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerService {

    public static void main(String[] args) {
        Queue<Integer> buffer = new LinkedList<>();
        int size = 10;

        Runnable producer = new Producer(buffer, size);

        Runnable consumer = new Consumer(buffer, size);

        System.out.println("Starting the process");

        new Thread(producer).start();
        new Thread(consumer).start();

        System.out.println("End of main method");

    }
}
