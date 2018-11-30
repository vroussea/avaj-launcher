package flyable;

import exception.IllegalCoordinatesException;
import exception.IllegalTypeArgumentException;
import org.junit.Test;

import static flyable.AircraftFactory.newAircraft;
import static org.junit.Assert.*;

public class AircraftFactoryTests {

    @Test(expected = IllegalTypeArgumentException.class)
    public void testWrongAircraftType() {
        newAircraft(
                "wrongType",
                "test",
                1,
                1,
                1);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testWrongLongitude() {
        newAircraft(
                "baloon",
                "test",
                -1,
                1,
                1);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testWrongLatitude() {
        newAircraft(
                "jetplane",
                "test",
                1,
                -1,
                1);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testWrongHeight() {
        newAircraft(
                "helicopter",
                "test",
                1,
                1,
                -1);
    }

    @Test
    public void testHeightTooHigh() {
        Flyable aircraft = newAircraft(
                "helicopter",
                "test",
                1,
                1,
                101);
        assertFalse(aircraft.toString().contains("101"));
    }

    @Test
    public void testBaloonIsCreated() {
        Flyable aircraft = newAircraft(
                "baloon",
                "test",
                1,
                1,
                100);
        assertEquals(aircraft.getClass(), Baloon.class);
    }

    @Test
    public void testHelicopterIsCreated() {
        Flyable aircraft = newAircraft(
                "helicopter",
                "test",
                1,
                1,
                100);
        assertEquals(aircraft.getClass(), Helicopter.class);
    }

    @Test
    public void testJetPlaneIsCreated() {
        Flyable aircraft = newAircraft(
                "jetplane",
                "test",
                1,
                1,
                100);
        assertEquals(aircraft.getClass(), JetPlane.class);
    }

    @Test
    public void testJetPlaneToString() {
        Flyable aircraft = newAircraft(
                "jetplane",
                "test",
                1,
                1,
                1);
        assertTrue(aircraft.toString().contains("JetPlane"));
    }

    @Test
    public void testHelicopterToString() {
        Flyable aircraft = newAircraft(
                "helicopter",
                "test",
                1,
                1,
                1);
        assertTrue(aircraft.toString().contains("Helicopter"));
    }

    @Test
    public void testBaloonToString() {
        Flyable aircraft = newAircraft(
                "baloon",
                "test",
                1,
                1,
                1);
        assertTrue(aircraft.toString().contains("Baloon"));
    }
}
