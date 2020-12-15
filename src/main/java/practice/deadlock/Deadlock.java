package practice.deadlock;

public class Deadlock {

    public static void main(String[] args) {

        Shared s1 = new Shared();

        Shared s2 = new Shared();

        System.out.println("main method start"+ Thread.currentThread().getName());
        Thread1 thread1 = new Thread1(s1, s2);
        thread1.start();
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread2 thread2 = new Thread2(s1,s2);
        thread2.start();
        System.out.println("main method end"+ Thread.currentThread().getName());
    }
}
