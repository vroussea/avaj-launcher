package flyable;

public class JetPlane extends Aircraft implements Flyable {
    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
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
