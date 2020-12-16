package practice.deadlock;

public class Shared {

    synchronized void test1(Shared s1, Shared s2){
        System.out.println("test-1 begins" + Thread.currentThread().getName());
        Util.sleep(1000);
        s2.test2(s1, s2);
        System.out.println("test-1 ends" + Thread.currentThread().getName());
    }

    synchronized void test2(Shared s1, Shared s2) {
        System.out.println("test-2 begins" + Thread.currentThread().getName());
        Util.sleep(1000);
        //s1.test1(s1, s2);
        System.out.println("test-2 ends" + Thread.currentThread().getName());
    }
}
