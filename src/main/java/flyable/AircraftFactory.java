package flyable;

import exception.IllegalCoordinatesException;
import exception.IllegalTypeArgumentException;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        switch (type) {
            case "helicopter":
                return newHelicopter(name, longitude, latitude, height);

            case "jetplane":
                return newJetPlane(name, longitude, latitude, height);

            case "baloon":
                return newBaloon(name, longitude, latitude, height);

            default:
                throw new IllegalTypeArgumentException(type);
        }
    }

    private static Flyable newHelicopter(String name, int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw new IllegalCoordinatesException();

        return new Helicopter(name, CoordinatesBuilder
                .aCoordinates()
                .withLongitude(longitude)
                .withLatitude(latitude)
                .withHeight(setHeight(height))
                .build());
    }

    private static Flyable newJetPlane(String name, int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw new IllegalCoordinatesException();

        return new JetPlane(name, CoordinatesBuilder
                .aCoordinates()
                .withLongitude(longitude)
                .withLatitude(latitude)
                .withHeight(setHeight(height))
                .build());
    }

    private static Flyable newBaloon(String name, int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw new IllegalCoordinatesException();

        return new Baloon(name, CoordinatesBuilder
                        .aCoordinates()
                        .withLongitude(longitude)
                        .withLatitude(latitude)
                        .withHeight(setHeight(height))
                        .build());
    }

    private static int setHeight(int height) {
        final int MAX_HEIGHT = 100;
        return height > MAX_HEIGHT ? MAX_HEIGHT : height;
    }
}
