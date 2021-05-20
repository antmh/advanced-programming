package commandline.commands;

import java.util.Optional;

import social.Person;

public class CommandState {
	private Optional<Person> person;

	public CommandState() {
		person = Optional.empty();
	}

	public Optional<Person> getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = Optional.of(person);
	}
}
