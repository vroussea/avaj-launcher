package flyable;

import tower.WeatherTower;

public interface Flyable {
    String toString();

    void updateConditions();

    void registerTower(WeatherTower weatherTower);
}
