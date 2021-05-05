import social.Network;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		var network = new Network();
		var listener = new Listener(network);
		var listenerThread = new Thread(listener);
		var server = new Server(network);
		System.out.println("Starting...");
		listenerThread.start();
		server.start();
		try {
			synchronized (network) {
				network.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Server stopped");
		listener.close();
		server.stop();
		System.out.println("Joining with listener");
		try {
			listenerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
