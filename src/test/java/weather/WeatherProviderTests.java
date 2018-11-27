package weather;

import flyable.Coordinates;
import flyable.CoordinatesBuilder;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WeatherProviderTests {
    @Test
    public void testOnlyOneInstancePossible() {
        WeatherProvider weatherProvider1 = WeatherProvider.getProvider();
        WeatherProvider weatherProvider2 = WeatherProvider.getProvider();

        assertEquals(weatherProvider1, weatherProvider2);
    }

    @Test
    public void testWeatherIsStillTheSameEachTime() {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();

        Coordinates coordinates = CoordinatesBuilder.aCoordinates().withHeight(1).withLatitude(1).withLongitude(1).build();

        ArrayList<String> weathers = new ArrayList<>(1000);

        for (int i = 0; i < 1000; i++) {
            weathers.add(weatherProvider.getCurrentWeather(coordinates));
        }
        String currentWeather = weathers.get(0);
        boolean isOneWeatherDifferent = false;
        for (String weather : weathers) {
            if (!weather.equals(currentWeather)) {
                isOneWeatherDifferent = true;
            }
        }

        assertFalse(isOneWeatherDifferent);
    }

    @Test
    public void testWeatherChangesAfterReinitialisation() {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();

        Coordinates coordinates = CoordinatesBuilder.aCoordinates().withHeight(1).withLatitude(1).withLongitude(1).build();

        ArrayList<String> weathers = new ArrayList<>(1000);

        for (int i = 0; i < 1000; i++) {
            weathers.add(weatherProvider.getCurrentWeather(coordinates));
            weatherProvider.restartSimulation();
        }
        String currentWeather = weathers.get(0);
        boolean isOneWeatherDifferent = false;
        for (String weather : weathers) {
            if (!weather.equals(currentWeather)) {
                isOneWeatherDifferent = true;
            }
        }

        assertTrue(isOneWeatherDifferent);
    }
}
