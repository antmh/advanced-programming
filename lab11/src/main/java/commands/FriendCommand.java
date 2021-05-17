package commands;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import social.Person;

public class FriendCommand extends Command {
	static Pattern pattern = Pattern.compile("friend\\s+(?<friends>.*)");

	public FriendCommand(Network network, CommandState state) {
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
		String[] names = matcher.group("friends").split(" ");
		for (var name : names) {
			if (!network.addFriend(person.get().getName(), name)) {
				return "Error: could not add " + name + " as a friend";
			}
		}
		return "Added friends successfully";
	}
}
