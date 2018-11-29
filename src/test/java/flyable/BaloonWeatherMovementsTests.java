package flyable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tower.WeatherTower;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaloonWeatherMovementsTests {

    private WeatherTower weatherTower;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Before
    public void init() {
        weatherTower = mock(WeatherTower.class);
    }

    @Test
    public void testSunMovement() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SUN");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(baloon.toString().contains("coordinates=Coordinates{longitude=3, latitude=1, height=5}"));
    }

    @Test
    public void testRainMovement() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("RAIN");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(baloon.toString().contains("coordinates=Coordinates{longitude=1, latitude=1, height=-4}"));
    }

    @Test
    public void testFogMovement() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("FOG");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(baloon.toString().contains("coordinates=Coordinates{longitude=1, latitude=1, height=-2}"));
    }

    @Test
    public void testSnowMovement() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SNOW");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(baloon.toString().contains("coordinates=Coordinates{longitude=1, latitude=1, height=-14}"));
    }

    @Test
    public void testSunDialog() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SUN");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(outContent.toString().contains("The sun is as round as my baloon !"));
    }

    @Test
    public void testRainDialog() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("RAIN");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(outContent.toString().contains("The rain makes my baloon too heavy, I'm falling !"));
    }

    @Test
    public void testFogDialog() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("FOG");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(outContent.toString().contains("Where am I ?"));
    }

    @Test
    public void testSnowDialog() {

        Flyable baloon = AircraftFactory.newAircraft("baloon", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SNOW");
        baloon.registerTower(weatherTower);
        baloon.updateConditions();

        assertTrue(outContent.toString().contains("Brrrrr."));
    }
}
