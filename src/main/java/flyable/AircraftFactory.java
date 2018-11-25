package flyable;

import exception.IllegalCoordinatesException;
import exception.IllegalTypeArgument;

public class AircraftFactory {

    private final int MAXHEIGHT = 100;

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        switch (type) {
            case "helicopter":
                return newHelicopter(name, longitude, latitude, height);

            case "jetPlane":
                return newJetPlane(name, longitude, latitude, height);

            case "baloon":
                return newBaloon(name, longitude, latitude, height);

            default:
                throw new IllegalTypeArgument();
        }
    }

    private Flyable newHelicopter(String name, int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw  new IllegalCoordinatesException();

        return null;//new Helicopter(name, longitude, latitude, height > 100 ? 100 : height);
    }

    private Flyable newJetPlane(String name, int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw  new IllegalCoordinatesException();

        return null;//new JetPlane(name, longitude, latitude, height > 100 ? 100 : height);
    }

    private Flyable newBaloon(String name, int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw  new IllegalCoordinatesException();

        return new Baloon(name, CoordinatesBuilder
                        .aCoordinates()
                        .withLongitude(longitude)
                        .withLatitude(latitude)
                        .withHeight(setHeight(height))
                        .build());
    }

    private int setHeight(int height) {
        return height > MAXHEIGHT ? MAXHEIGHT : height;
    }
}
