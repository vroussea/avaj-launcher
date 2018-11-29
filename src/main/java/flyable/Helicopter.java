package flyable;

import exception.NoWeatherTowerSpecifiedException;
import tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        Coordinates sunCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(2)
                .withLatitude(0)
                .withLongitude(10)
                .build();
        Coordinates rainCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(0)
                .withLatitude(0)
                .withLongitude(5)
                .build();
        Coordinates fogCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(0)
                .withLatitude(0)
                .withLongitude(1)
                .build();
        Coordinates snowCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(-12)
                .withLatitude(0)
                .withLongitude(0)
                .build();
        weatherMovements.put("SUN", sunCoordinates);
        weatherMovements.put("RAIN", rainCoordinates);
        weatherMovements.put("FOG", fogCoordinates);
        weatherMovements.put("SNOW", snowCoordinates);
        weatherDialogs.put("SUN", "Mixing the air.");
        weatherDialogs.put("RAIN", "Boring.");
        weatherDialogs.put("FOG", "Did I just see Santa ?");
        weatherDialogs.put("SNOW", "I should stop my rotor it's already cold enough.");
    }

    @Override
    public void updateConditions() {
        if (weatherTower == null)
            throw new NoWeatherTowerSpecifiedException();
        String weather = weatherTower.getWeather(this.coordinates);

        this.coordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(checkNotMoreThan(this.coordinates.getHeight() + weatherMovements.get(weather).getHeight(), 100))
                .withLatitude(this.coordinates.getLatitude() + weatherMovements.get(weather).getLatitude())
                .withLongitude(this.coordinates.getLongitude() + weatherMovements.get(weather).getLongitude())
                .build();
        System.out.println("Helicopter#" + name + "(" + id + "): " + weatherDialogs.get(weather));
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
