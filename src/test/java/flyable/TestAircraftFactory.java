package flyable;

import exception.IllegalCoordinatesException;
import exception.IllegalTypeArgument;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TestAircraftFactory {

    private AircraftFactory aircraftFactory;

    @Before
    public void before() {
        aircraftFactory = new AircraftFactory();
    }

    @Test(expected = IllegalTypeArgument.class)
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
                "baloon",
                "test",
                1,
                -1,
                1);
    }

    public void testHeightTooHigh() {
        Flyable aicraft = aircraftFactory.newAircraft(
                "baloon",
                "test",
                1,
                1,
                101);
        assertFalse(aicraft.toString().contains("101"));
    }
}
