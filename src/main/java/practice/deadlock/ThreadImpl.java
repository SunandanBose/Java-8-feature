package practice.deadlock;

public class ThreadImpl {

    public static void main(String[] args) {
        Thread3 thread = new Thread3();
        thread.start();
        thread.interrupt();
    }
}
