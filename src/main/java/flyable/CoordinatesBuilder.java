package flyable;

public final class CoordinatesBuilder {
    private int longitude;
    private int latitude;
    private int height;

    private CoordinatesBuilder() {
    }

    static CoordinatesBuilder aCoordinates() {
        return new CoordinatesBuilder();
    }

    CoordinatesBuilder withLongitude(int longitude) {
        this.longitude = longitude;
        return this;
    }

    CoordinatesBuilder withLatitude(int latitude) {
        this.latitude = latitude;
        return this;
    }

    CoordinatesBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    Coordinates build() {
        return new Coordinates(longitude, latitude, height);
    }
}
