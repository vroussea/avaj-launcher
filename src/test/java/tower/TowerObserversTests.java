package tower;

import exception.AlreadyRegisteredObserverException;
import exception.NotRegisteredObserverException;
import flyable.Flyable;
import org.junit.Before;
import org.junit.Test;

import static flyable.AircraftFactory.newAircraft;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TowerObserversTests {
    private Tower tower;

    @Before
    public void init() {
        tower = new Tower();
    }

    @Test
    public void testRegisterFlyable() {
        Flyable flyable = newAircraft("jetPlane", "test", 1, 1, 1);

        tower.register(flyable);
        assertEquals(tower.getObservers().size(), 1);
    }

    @Test
    public void testRegisterMultipleFlyable() {
        Flyable flyable1 = newAircraft("jetPlane", "test", 1, 1, 1);
        Flyable flyable2 = newAircraft("jetPlane", "test", 1, 1, 1);
        Flyable flyable3 = newAircraft("jetPlane", "test", 1, 1, 1);

        tower.register(flyable1);
        tower.register(flyable2);
        tower.register(flyable3);
        assertEquals(tower.getObservers().size(), 3);
    }

    @Test
    public void testUnregisterFlyable() {
        Flyable flyable = newAircraft("jetPlane", "test", 1, 1, 1);

        tower.register(flyable);
        tower.unregister(flyable);
        assertEquals(tower.getObservers().size(), 0);
    }

    @Test(expected = NotRegisteredObserverException.class)
    public void testUnregisterWhenNoFlyable() {
        Flyable flyable = newAircraft("jetPlane", "test", 1, 1, 1);

        tower.unregister(flyable);
    }

    @Test(expected = NotRegisteredObserverException.class)
    public void testUnregisterWrongFlyable() {
        Flyable flyable1 = newAircraft("jetPlane", "test", 1, 1, 1);
        Flyable flyable2 = newAircraft("jetPlane", "test", 1, 1, 1);

        tower.register(flyable1);
        tower.unregister(flyable2);
    }

    @Test(expected = AlreadyRegisteredObserverException.class)
    public void testRegisterFlyableAlreadyRegistered() {
        Flyable flyable = newAircraft("jetPlane", "test", 1, 1, 1);

        tower.register(flyable);
        tower.register(flyable);
    }

    @Test
    public void testObserverUpdated() {
        Flyable flyable = mock(Flyable.class);

        tower.register(flyable);

        tower.conditionsChanged();

        verify(flyable, times(1)).updateConditions();
    }
}
