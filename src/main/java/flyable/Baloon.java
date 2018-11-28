package flyable;

import exception.NoWeatherTowerSpecifiedException;
import tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        if (weatherTower == null)
            throw new NoWeatherTowerSpecifiedException();
        String weather = weatherTower.getWeather(this.coordinates);

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public String toString() {
        return "Baloon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
