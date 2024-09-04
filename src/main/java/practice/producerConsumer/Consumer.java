package practice.producerConsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            Message msg;
            while (!(msg = queue.take()).getMessage().equals("exit")) {
//                System.out.println("Start Queue");
//                queue.stream().forEach((i) -> i.getMessage());
//                System.out.println("End Queue");

                Thread.sleep(10);
                System.out.println("Consumed " + msg.getMessage() );
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
