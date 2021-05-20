package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import social.Person;

public class RegisterCommand extends Command {
	static Pattern pattern = Pattern.compile("register\\s+(?<name>[^\\s]+)");

	public RegisterCommand(Network network, CommandState state) {
		super(network, state);
	}

	@Override
	protected Pattern getPattern() {
		return pattern;
	}

	@Override
	protected String execute(Matcher matcher) {
		String name = matcher.group("name");
		if (network.register(new Person(name))) {
			return name + " connected successfully";
		} else {
			return "Error: user " + name + " already exists";
		}
	}
}
