package flyable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tower.WeatherTower;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelicopterWeatherMovementsTests {

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

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SUN");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(helicopter.toString().contains("coordinates=Coordinates{longitude=11, latitude=1, height=3}"));
    }

    @Test
    public void testRainMovement() {

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("RAIN");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(helicopter.toString().contains("coordinates=Coordinates{longitude=6, latitude=1, height=1}"));
    }

    @Test
    public void testFogMovement() {

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("FOG");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(helicopter.toString().contains("coordinates=Coordinates{longitude=2, latitude=1, height=1}"));
    }

    @Test
    public void testSnowMovement() {

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SNOW");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(helicopter.toString().contains("coordinates=Coordinates{longitude=1, latitude=1, height=-11}"));
    }

    @Test
    public void testSunDialog() {

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SUN");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(outContent.toString().contains("Mixing the air."));
    }

    @Test
    public void testRainDialog() {

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("RAIN");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(outContent.toString().contains("Boring."));
    }

    @Test
    public void testFogDialog() {

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("FOG");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(outContent.toString().contains("Did I just see Santa ?"));
    }

    @Test
    public void testSnowDialog() {

        Flyable helicopter = AircraftFactory.newAircraft("helicopter", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SNOW");
        helicopter.registerTower(weatherTower);
        helicopter.updateConditions();

        assertTrue(outContent.toString().contains("I should stop my rotor it's already cold enough."));
    }
}
