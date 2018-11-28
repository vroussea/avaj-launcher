package exception;

public class IllegalTypeArgumentException extends IllegalArgumentException {
    public IllegalTypeArgumentException() {
        super("Wrong type for this flyable.");
    }
}
