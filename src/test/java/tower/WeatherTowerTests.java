package tower;

import flyable.Coordinates;
import flyable.CoordinatesBuilder;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class WeatherTowerTests {
    @Test
    public void testConditionsChanged() {
        WeatherTower tower = new WeatherTower();
        Coordinates coordinates = CoordinatesBuilder.aCoordinates().withHeight(1).withLatitude(1).withLongitude(1).build();
        String weather = tower.getWeather(coordinates);

        boolean isWeatherChanged = true;
        for (int i = 0; i < 1000; i++) {
            tower.changeWeather();
            if (!weather.equals(tower.getWeather(coordinates)))
                isWeatherChanged = false;
        }

        assertFalse(isWeatherChanged);
    }
}
