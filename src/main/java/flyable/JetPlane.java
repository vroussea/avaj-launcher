package flyable;

import exception.NoWeatherTowerSpecifiedException;
import tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        Coordinates sunCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(2)
                .withLatitude(10)
                .withLongitude(0)
                .build();
        Coordinates rainCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(0)
                .withLatitude(5)
                .withLongitude(0)
                .build();
        Coordinates fogCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(0)
                .withLatitude(1)
                .withLongitude(0)
                .build();
        Coordinates snowCoordinates = CoordinatesBuilder.aCoordinates()
                .withHeight(-7)
                .withLatitude(0)
                .withLongitude(0)
                .build();
        weatherMovements.put("SUN", sunCoordinates);
        weatherMovements.put("RAIN", rainCoordinates);
        weatherMovements.put("FOG", fogCoordinates);
        weatherMovements.put("SNOW", snowCoordinates);
        weatherDialogs.put("SUN", "What a nice day.");
        weatherDialogs.put("RAIN", "I should fly a little slower.");
        weatherDialogs.put("FOG", "OMG SILENT HILL !");
        weatherDialogs.put("SNOW", "Am I in Northrend ?");
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
        System.out.println("JetPlane#" + name + "(" + id + "): " + weatherDialogs.get(weather));
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.");
            this.weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public String toString() {
        return "JetPlane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
