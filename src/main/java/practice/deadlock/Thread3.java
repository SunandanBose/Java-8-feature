package practice.deadlock;

public class Thread3 extends Thread{

    @Override
    public void run(){

        System.out.println("start execution of "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end execution of "+ Thread.currentThread().getName());


    }
}
