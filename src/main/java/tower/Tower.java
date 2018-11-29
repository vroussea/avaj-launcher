package tower;

import exception.AlreadyRegisteredObserverException;
import exception.NoWeatherTowerSpecifiedException;
import exception.NotRegisteredObserverException;
import flyable.Flyable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower {
    private final CopyOnWriteArrayList<Flyable> observers = new CopyOnWriteArrayList<>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable))
            throw new AlreadyRegisteredObserverException();

        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        if (!observers.remove(flyable))
            throw new NotRegisteredObserverException();
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }

    public CopyOnWriteArrayList<Flyable> getObservers() {
        return (CopyOnWriteArrayList<Flyable>) observers.clone();
    }
}
