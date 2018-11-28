package tower;

import exception.AlreadyRegisteredObserverException;
import exception.NoWeatherTowerSpecifiedException;
import exception.NotRegisteredObserverException;
import flyable.Flyable;

import java.util.ArrayList;

public class Tower {
    private final ArrayList<Flyable> observers = new ArrayList<>();

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
            try {
                flyable.updateConditions();
            } catch (NoWeatherTowerSpecifiedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Flyable> getObservers() {
        return (ArrayList<Flyable>) observers.clone();
    }
}
