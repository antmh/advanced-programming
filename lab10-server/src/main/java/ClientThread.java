import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import commands.Command;
import commands.CommandState;
import commands.FriendCommand;
import commands.LoginCommand;
import commands.ReadCommand;
import commands.RegisterCommand;
import commands.SendCommand;
import commands.StopCommand;
import social.Network;

public class ClientThread extends Thread {
	private Socket socket;
	private List<Command> commands;
	private CommandState state;

	public ClientThread(Socket socket, Network network) {
		this.socket = socket;
		this.state = new CommandState();
		this.commands = new ArrayList<>();
		commands.add(new RegisterCommand(network, state));
		commands.add(new LoginCommand(network, state));
		commands.add(new FriendCommand(network, state));
		commands.add(new ReadCommand(network, state));
		commands.add(new SendCommand(network, state));
		commands.add(new StopCommand(network, state));
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			while (true) {
				String request = null;
				try {
					request = in.readLine();
				} catch (SocketTimeoutException e) {
					System.out.println("Timeout expired");
					break;
				}
				if (request.equals("exit")) {
					break;
				}
				String response = "Invalid command";
				for (var command : commands) {
					Optional<String> result = command.maybeExecute(request);
					if (result.isPresent()) {
						response = result.get();
						break;
					}
				}
				out.println(response);
				out.flush();
			}
		} catch (IOException e) {
			System.err.println("Communication error " + e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
