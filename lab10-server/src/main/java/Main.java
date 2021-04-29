import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import social.Network;

public class Main {
	public static void main(String[] args) {
		Network network = new Network();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8100);
			while (true) {
				System.out.println("Waiting");
				Socket socket = serverSocket.accept();
				new ClientThread(socket, network).start();
			}
		} catch (IOException e) {
			System.err.println("Communication error: " + e);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
