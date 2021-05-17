package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StopCommand extends Command {
	static Pattern pattern = Pattern.compile("stop");

	public StopCommand(Network network, CommandState state) {
		super(network, state);
	}

	@Override
	protected Pattern getPattern() {
		return pattern;
	}

	@Override
	protected String execute(Matcher matcher) {
		if (network.stopNewConnections()) {
			return "Server stopped successfully";
		}
		return "Server already stopped";
	}
}
