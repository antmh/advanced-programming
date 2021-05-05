import social.Network;

public class Main {
	public static void main(String[] args) {
		var network = new Network();
		var listener = new Listener(network);
		var listenerThread = new Thread(listener);
		System.out.println("Starting...");
		listenerThread.start();
		try {
			synchronized (network) {
				network.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Server stopped");
		listener.close();
		System.out.println("Joining with listener");
		try {
			listenerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
