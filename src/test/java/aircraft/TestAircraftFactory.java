package aircraft;

import exception.IllegalCoordinatesException;
import exception.IllegalTypeArgument;
import flyable.AircraftFactory;
import flyable.Flyable;
import org.junit.Test;

public class TestAircraftFactory {
    private AircraftFactory aircraftFactory;

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

    @Test(expected = IllegalCoordinatesException.class)
    public void testHeightTooHigh() {
        aircraftFactory.newAircraft(
                "baloon",
                "test",
                1,
                1,
                101);
    }


    @Test
    public void testAircraftCreated() {
        Flyable newAircraft = aircraftFactory.newAircraft(
                "wrongType",
                "test",
                1,
                1,
                1);
        assert(newAircraft.toString().equals("test"));
    }
}
