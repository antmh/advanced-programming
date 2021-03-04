package lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class City {
	private List<Location> locations;
	private String name;

	public City() {
		locations = new ArrayList<>();
	}

	public City(String name) {
		locations = new ArrayList<>();
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("City [name=" + name + "] { ");
		for (int index = 0; index < locations.size(); ++index) {
			builder.append(locations.get(index));
			if (index != locations.size() - 1) {
				builder.append(", ");
			} else {
				builder.append(" ");
			}
		}
		builder.append("}");
		return builder.toString();
	}

	public void addLocation(Location location) {
		if (location == null) {
			throw new IllegalArgumentException("Location cannot be null");
		}
		locations.add(location);
	}

	public void displayVisitableNotPayable() {
		List<Visitable> result = new ArrayList<>();

		Iterator<Location> iterator = locations.iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (element instanceof Visitable && !(element instanceof Payable)) {
				result.add((Visitable) element);
			}
		}

		Collections.sort(result, Visitable::compareByOpeningHour);

		Iterator<Visitable> resultIterator = result.iterator();
		while (resultIterator.hasNext()) {
			System.out.println(resultIterator.next());
		}
	}
}
