package flyable;

public class Helicopter extends Aircraft implements Flyable {
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
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
