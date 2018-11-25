package flyable;

public class Baloon extends Aircraft implements Flyable {
    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
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
