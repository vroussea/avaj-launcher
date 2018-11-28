package tower;

import flyable.Coordinates;
import flyable.Flyable;
import weather.WeatherProvider;

public class WeatherTower extends Tower {
    private final WeatherProvider weatherProvider = WeatherProvider.getProvider();

    @Override
    public void register(Flyable flyable) {
        super.register(flyable);
        flyable.registerTower(this);
    }

    @Override
    public void unregister(Flyable flyable) {
        super.unregister(flyable);
        flyable.registerTower(null);
    }

    public String getWeather(Coordinates coordinates) {
        return weatherProvider.getCurrentWeather(coordinates);
    }

    void changeWeather() {
        super.conditionsChanged();
    }
}
