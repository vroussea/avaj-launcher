package weather;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeatherProviderTests {
    @Test
    public void testOnlyOneInstancePossible() {
        WeatherProvider weatherProvider1 = WeatherProvider.getProvider();
        WeatherProvider weatherProvider2 = WeatherProvider.getProvider();

        assertEquals(weatherProvider1, weatherProvider2);
    }
}
