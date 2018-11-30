package exception;

public class IllegalTypeArgumentException extends IllegalArgumentException {
    public IllegalTypeArgumentException(String error) {
        super("Wrong type for this flyable : " + error);
    }
}
