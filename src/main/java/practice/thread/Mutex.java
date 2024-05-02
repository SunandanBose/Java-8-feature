package practice.thread;

public class Mutex {
    private int counter = 0;
    private final Object mutexLock = new Object();

    public void increment(){
        synchronized (mutexLock) {
            counter += 1;
        }
    }

    /* In multiple threads if the counter is being incremented
        the get will have to wait for the lock to be free
    */
    public int get(){
        synchronized (mutexLock) {
            return counter;
        }
    }

    public static void main(String[] args) {

    }
}
