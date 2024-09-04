package practice;


import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircuitBreakerTest {
    @Test
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

    @Test
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