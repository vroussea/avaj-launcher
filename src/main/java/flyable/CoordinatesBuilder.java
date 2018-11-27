package flyable;

public final class CoordinatesBuilder {
    private int longitude;
    private int latitude;
    private int height;

    private CoordinatesBuilder() {
    }

    public static CoordinatesBuilder aCoordinates() {
        return new CoordinatesBuilder();
    }

    public CoordinatesBuilder withLongitude(int longitude) {
        this.longitude = longitude;
        return this;
    }

    public CoordinatesBuilder withLatitude(int latitude) {
        this.latitude = latitude;
        return this;
    }

    public CoordinatesBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public Coordinates build() {
        return new Coordinates(longitude, latitude, height);
    }
}
