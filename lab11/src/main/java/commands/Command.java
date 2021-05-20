package commands;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
	protected Network network;
	protected CommandState state;

	public Command(Network network, CommandState state) {
		this.network = network;
		this.state = state;
	}

	public Optional<String> maybeExecute(String input) {
		Matcher matcher = getPattern().matcher(input);
		if (!matcher.matches()) {
			return Optional.empty();
		}
		return Optional.of(execute(matcher));
	}

	protected abstract Pattern getPattern();

	protected abstract String execute(Matcher matcher);
}
