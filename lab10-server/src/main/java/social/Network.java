package social;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Network {
	private Set<Person> people;
	
	public Network() {
		people = new HashSet<>();
	}
	
	public boolean register(Person person) {
		for (var existentPerson : people) {
			if (person.getName().equals(existentPerson.getName())) {
				return false;
			}
		}
		people.add(person);
		return true;
	}
	
	public Optional<Person> findPerson(String name) {
		for (var person : people) {
			if (person.getName().equals(name)) {
				return Optional.of(person);
			}
		}
		return Optional.empty();
	}
}
