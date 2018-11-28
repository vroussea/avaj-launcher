package exception;

public class NoWeatherTowerSpecifiedException extends RuntimeException {
    public NoWeatherTowerSpecifiedException() {
        super("No weather tower has been specified for this flyable.");
    }
}