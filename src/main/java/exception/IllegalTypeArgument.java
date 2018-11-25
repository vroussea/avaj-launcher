package exception;

public class IllegalTypeArgument extends IllegalArgumentException {
    public IllegalTypeArgument() {
        super("Wrong type for this flyable.");
    }
}
