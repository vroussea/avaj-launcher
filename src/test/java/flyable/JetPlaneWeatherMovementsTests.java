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

public class JetPlaneWeatherMovementsTests {

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

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SUN");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(jetPlane.toString().contains("coordinates=Coordinates{longitude=1, latitude=11, height=3}"));
    }

    @Test
    public void testRainMovement() {

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("RAIN");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(jetPlane.toString().contains("coordinates=Coordinates{longitude=1, latitude=6, height=1}"));
    }

    @Test
    public void testFogMovement() {

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("FOG");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(jetPlane.toString().contains("coordinates=Coordinates{longitude=1, latitude=2, height=1}"));
    }

    @Test
    public void testSnowMovement() {

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SNOW");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(jetPlane.toString().contains("coordinates=Coordinates{longitude=1, latitude=1, height=-6}"));
    }

    @Test
    public void testSunDialog() {

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SUN");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(outContent.toString().contains("What a nice day."));
    }

    @Test
    public void testRainDialog() {

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("RAIN");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(outContent.toString().contains("I should fly a little slower."));
    }

    @Test
    public void testFogDialog() {

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("FOG");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(outContent.toString().contains("OMG SILENT HILL !"));
    }

    @Test
    public void testSnowDialog() {

        Flyable jetPlane = AircraftFactory.newAircraft("jetPlane", "test", 1, 1, 1);

        when(weatherTower.getWeather(any())).thenReturn("SNOW");
        jetPlane.registerTower(weatherTower);
        jetPlane.updateConditions();

        assertTrue(outContent.toString().contains("Am I in Northrend ?"));
    }
}
