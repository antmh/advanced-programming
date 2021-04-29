import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 8100);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String request = console.readLine();
				if (request.equals("exit")) {
					break;
				}
				out.println(request);
				String response = in.readLine();
				System.out.println(response);
			}
			out.println("exit");
		} catch (UnknownHostException e) {
			System.err.println("No server: " + e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
