package exception;

public class NotRegisteredObserverException extends RuntimeException {
    public NotRegisteredObserverException() {
        super("Cannot unregister not registered flyable.");
    }
}