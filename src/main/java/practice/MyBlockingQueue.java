package practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    private Queue<T> queue = new LinkedList<T>();

    private Lock lock;

    private int limit;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        this.lock = new ReentrantLock();
    }

    public void put(T value){
        lock.lock();
        try{
            boolean didWait = false;
            while(isFull()){
                didWait = true;
                wait();
            }
            queue.add(value);
            if(didWait)
                notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        lock.lock();
        try{
            boolean didWait = false;
            while(isEmpty()){
                didWait = true;
                wait();
            }
            t = queue.poll();
            if(didWait)
                notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    private boolean isEmpty() {
        return queue.size() == 0;
    }

    private boolean isFull() {
        return queue.size() == limit;
    }
}
