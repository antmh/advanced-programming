package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import commands.Network;

public class Listener implements Runnable {
	private static final int PORT = 8100;
	private static final int N_THREADS = 1000;
	private static final int SOCKET_TIMEOUT = 5 * 60 * 1000;
	private ServerSocket serverSocket;
	private Network network;
	private List<Future<?>> futures;
	private ExecutorService threadPool;

	public Listener(Network network) {
		this.network = network;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		futures = new ArrayList<>();
		threadPool = Executors.newFixedThreadPool(N_THREADS);

	}

	@Override
	public void run() {
		while (!serverSocket.isClosed()) {
			System.out.println("Waiting new connection");
			try {
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(SOCKET_TIMEOUT);
				futures.add(threadPool.submit(new ClientThread(socket, network)));
			} catch (SocketException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Server socket is closed");
	}

	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (var future : futures) {
			System.out.println("Waiting for thread to finish");
			if (!future.isDone()) {
				try {
					future.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		threadPool.shutdown();
		System.out.println("All threads are finished");
	}
}
