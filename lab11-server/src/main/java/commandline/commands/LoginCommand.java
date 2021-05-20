package commandline.commands;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commandline.Network;
import social.Person;

public class LoginCommand extends Command {
	static Pattern pattern = Pattern.compile("login\\s+(?<name>[^\\s]+)");

	public LoginCommand(Network network, CommandState state) {
		super(network, state);
	}

	@Override
	protected Pattern getPattern() {
		return pattern;
	}

	@Override
	protected String execute(Matcher matcher) {
		String name = matcher.group("name");
		Optional<Person> person = network.findPerson(name);
		if (person.isPresent()) {
			state.setPerson(person.get());
			return name + " connected successfully";
		} else {
			return "Error: user " + name + " does not exist";
		}
	}
}
