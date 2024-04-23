package practice;

public class CircuitBreakerException extends Exception {
    public CircuitBreakerException(String message) {
        super(message);
    }
}
