package practice.deadlock;

public class Thread1 extends Thread{

    private Shared s1;

    private Shared s2;

    public Thread1(Shared s1, Shared s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run(){
        s1.test1(s1, s2);
        Thread t1 = new Thread3();
        t1.start();
        Thread t2 = new Thread3();
        t2.start();
        Thread t3 = new Thread3();
        t3.start();

        // Thread join is used in places where we need the input from 3 API's
        // and whant to handle the result similar to a promise in JS. Here the 3 Thread
        // will start paralelly but will wait for the response. before mocing back to main thread.
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
