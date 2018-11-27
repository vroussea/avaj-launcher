package weather;

import flyable.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherProvider {
    private final static WeatherProvider weatherProvider = new WeatherProvider();
    private Map<Integer, String> weather = new HashMap<>();

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int index = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();

        if (weather.get(index) != null)
            return weather.get(index);

        Random random = new Random();
        int randomWeather = random.nextInt(4);

        weather.put(index, weatherFromNumber(randomWeather));
        return weather.get(index);
    }

    private String weatherFromNumber(int number) {
        String weather = "RAIN";

        switch (number) {
            case 1:
                weather = "FOG";
                break;
            case 2:
                weather = "SUN";
                break;
            case 3:
                weather = "SNOW";
                break;
        }

        return weather;
    }

    public void restartSimulation() {
        weather = new HashMap<>();
    }
}
