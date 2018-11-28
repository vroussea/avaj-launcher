package flyable;

import exception.IllegalCoordinatesException;
import exception.IllegalTypeArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AircraftFactoryTests {

    private AircraftFactory aircraftFactory;

    @Before
    public void before() {
        aircraftFactory = new AircraftFactory();
    }

    @Test(expected = IllegalTypeArgumentException.class)
    public void testWrongAircraftType() {
        aircraftFactory.newAircraft(
                "wrongType",
                "test",
                1,
                1,
                1);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testWrongLongitude() {
        aircraftFactory.newAircraft(
                "baloon",
                "test",
                -1,
                1,
                1);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testWrongLatitude() {
        aircraftFactory.newAircraft(
                "jetPlane",
                "test",
                1,
                -1,
                1);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testWrongHeight() {
        aircraftFactory.newAircraft(
                "helicopter",
                "test",
                1,
                1,
                -1);
    }

    @Test
    public void testHeightTooHigh() {
        Flyable aircraft = aircraftFactory.newAircraft(
                "helicopter",
                "test",
                1,
                1,
                101);
        assertFalse(aircraft.toString().contains("101"));
    }

    @Test
    public void testBaloonIsCreated() {
        Flyable aircraft = aircraftFactory.newAircraft(
                "baloon",
                "test",
                1,
                1,
                100);
        assertEquals(aircraft.getClass(), Baloon.class);
    }

    @Test
    public void testHelicopterIsCreated() {
        Flyable aircraft = aircraftFactory.newAircraft(
                "helicopter",
                "test",
                1,
                1,
                100);
        assertEquals(aircraft.getClass(), Helicopter.class);
    }

    @Test
    public void testJetPlaneIsCreated() {
        Flyable aircraft = aircraftFactory.newAircraft(
                "jetPlane",
                "test",
                1,
                1,
                100);
        assertEquals(aircraft.getClass(), JetPlane.class);
    }

    @Test
    public void testJetPlaneToString() {
        Flyable aircraft = aircraftFactory.newAircraft(
                "jetPlane",
                "test",
                1,
                1,
                1);
        assertTrue(aircraft.toString().contains("JetPlane"));
    }

    @Test
    public void testHelicopterToString() {
        Flyable aircraft = aircraftFactory.newAircraft(
                "helicopter",
                "test",
                1,
                1,
                1);
        assertTrue(aircraft.toString().contains("Helicopter"));
    }

    @Test
    public void testBaloonToString() {
        Flyable aircraft = aircraftFactory.newAircraft(
                "baloon",
                "test",
                1,
                1,
                1);
        assertTrue(aircraft.toString().contains("Baloon"));
    }
}
