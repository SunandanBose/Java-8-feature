package practice.thread;

import java.util.concurrent.ThreadFactory;

public class ThreadNaming  implements ThreadFactory {
    private static int count = 0;
    private static String name = "PoolWorker - ";

    @Override
    public Thread newThread(Runnable runnable) {
        Thread t = new Thread(runnable, name+ ++count);

        return t;
    }
}
