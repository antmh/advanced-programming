package commandline.commands;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commandline.Network;
import social.Person;

public class ReadCommand extends Command {
	static Pattern pattern = Pattern.compile("read");

	public ReadCommand(Network network, CommandState state) {
		super(network, state);
	}

	@Override
	protected Pattern getPattern() {
		return pattern;
	}

	@Override
	protected String execute(Matcher matcher) {
		Optional<Person> person = state.getPerson();
		if (person.isEmpty()) {
			return "Error: not logged in";
		}
		StringBuilder messages = new StringBuilder();
		for (var message : person.get().getMessages()) {
			messages.append(message.getSender());
			messages.append(": ");
			messages.append(message.getContent());
			messages.append("\n");
		}
		return messages.toString();
	}
}
