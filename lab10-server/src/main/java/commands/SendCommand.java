package commands;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import social.Network;
import social.Person;

public class SendCommand extends Command {
	static Pattern pattern = Pattern.compile("send\\s+(?<message>.+)");

	public SendCommand(Network network, CommandState state) {
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
		String message = matcher.group("message");
		person.get().sendMessage(message);
		return "Sent message successfully";
	}
}
