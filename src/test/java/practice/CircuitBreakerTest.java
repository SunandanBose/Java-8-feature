package practice;

import junit.framework.TestCase;

import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreakerTest extends TestCase {
    public void testCircuitBreakerDoesNotStopsAfter3CallsWhenNoException() throws Exception {
        CircuitBreaker circuitBreaker = new CircuitBreaker();
        AtomicInteger counter = new AtomicInteger(0);
        Callback codeToExecute = () -> {
            int i = counter.incrementAndGet();
            System.out.println(i);
        };
        circuitBreaker.register(codeToExecute, 3);
        circuitBreaker.execute();
        circuitBreaker.execute();
        circuitBreaker.execute();
        circuitBreaker.execute();
        circuitBreaker.execute();
        assertEquals(5, counter.get());
    }

    public void testCircuitBreakerStopsAfter3CallsOnlyOnException() throws Exception {
        CircuitBreaker circuitBreaker = new CircuitBreaker();
        AtomicInteger counter = new AtomicInteger(0);
        Callback codeToExecute = () -> {
            int i = counter.incrementAndGet();
            System.out.println(i);
            throw new Exception("break assert");
        };
        circuitBreaker.register(codeToExecute, 3);
        circuitBreaker.execute();
        circuitBreaker.execute();
        circuitBreaker.execute();
        circuitBreaker.execute();
        circuitBreaker.execute();
        assertEquals(3, counter.get());
    }

}