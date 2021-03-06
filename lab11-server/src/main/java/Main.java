import java.io.IOException;

import commandline.GraphServer;
import commandline.Listener;
import commandline.Network;
import spring.App;

public class Main {
	public static void main(String[] args) throws IOException {
		var network = new Network();
		var listener = new Listener(network);
		var listenerThread = new Thread(listener);
		var springThread = new Thread(new App(args));
		var server = new GraphServer(network);
		System.out.println("Starting...");
		listenerThread.start();
		server.start();
		springThread.start();
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
			springThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
