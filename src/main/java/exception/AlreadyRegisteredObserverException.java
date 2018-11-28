package exception;

public class AlreadyRegisteredObserverException extends RuntimeException {
    public AlreadyRegisteredObserverException() {
        super("Cannot register already registered flyable.");
    }
}