package flyable;

import java.util.HashMap;
import java.util.Map;

public class Aircraft {
	protected long          id;
	protected String        name;
	protected Coordinates   coordinates;
	private static long     idCounter = 0;

	protected Map<String, Coordinates> weatherMovements = new HashMap<>();
	protected Map<String, String> weatherDialogs = new HashMap<>();

	Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		id = nextId();
	}

	protected int checkNotMoreThan(int value, int bound) {
		return value > bound ? bound : value;
	}

	private long nextId() {
		return idCounter++;
	}
}
