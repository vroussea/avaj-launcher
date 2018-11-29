package weather;

import flyable.Coordinates;

public class WeatherProvider {
    private final static WeatherProvider weatherProvider = new WeatherProvider();
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int seed = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();

        return weather[seed % 4];
    }

}
