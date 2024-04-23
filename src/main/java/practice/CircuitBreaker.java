package practice;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreaker {
    private Callback fun;
    private int limit;
    private AtomicInteger times;

    public void register(Callback fun, int limit){

        this.fun = fun;
        this.limit = limit;
        this.times = new AtomicInteger(0);
    }


    public void execute() throws Exception {
        if (times.get() < limit) {
            try {
                fun.call();
            } catch (Exception e) {
                if (times.incrementAndGet() > limit) {
                    throw new CircuitBreakerException("Circuit Breaker limit reached");
                }
            }
        }
    }
}
