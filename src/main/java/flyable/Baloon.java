package flyable;

import exception.NoWeatherTowerSpecifiedException;
import tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        Coordinates sunCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(4)
                .withLatitude(0)
                .withLongitude(2)
                .build();
        Coordinates rainCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(-5)
                .withLatitude(0)
                .withLongitude(0)
                .build();
        Coordinates fogCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(-3)
                .withLatitude(0)
                .withLongitude(0)
                .build();
        Coordinates snowCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(-15)
                .withLatitude(0)
                .withLongitude(0)
                .build();
        weatherMovements.put("SUN", sunCoordinates);
        weatherMovements.put("RAIN", rainCoordinates);
        weatherMovements.put("FOG", fogCoordinates);
        weatherMovements.put("SNOW", snowCoordinates);
        weatherDialogs.put("SUN", "The sun is as round as my baloon !");
        weatherDialogs.put("RAIN", "The rain makes my baloon too heavy, I'm falling !");
        weatherDialogs.put("FOG", "Where am I ?");
        weatherDialogs.put("SNOW", "Brrrrr.");
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
        System.out.println("Baloon#" + name + "(" + id + "): " + weatherDialogs.get(weather));
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("Tower says: Baloon#" + name + "(" + id + ") unregistered from weather tower.");
            this.weatherTower.unregister(this);
        }
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
