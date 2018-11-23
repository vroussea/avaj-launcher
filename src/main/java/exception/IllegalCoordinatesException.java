package exception;

public class IllegalCoordinatesException extends IllegalArgumentException {
    public IllegalCoordinatesException() {
        super("Coordinates can't be under 0.");
    }
}