package commandline;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

public class GraphServer {
	private static final InetSocketAddress address = new InetSocketAddress("localhost", 8081);
	private HttpServer httpServer;
	private Network network;

	public GraphServer(Network network) {
		this.network = network;
		try {
			httpServer = HttpServer.create(address, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		var context = httpServer.createContext("/");
		context.setHandler(this::handle);
	}

	public void start() {
		httpServer.start();
	}

	public void stop() {
		httpServer.stop(0);
	}

	private void handle(HttpExchange exchange) {
		var graph = Graphviz.fromGraph(network.constructGraph()).render(Format.SVG).toString();
		var response = "<!DOCTYPE html><html><body>" + graph + "</body></html>";
		try {
			exchange.sendResponseHeaders(200, response.getBytes().length);
			exchange.getResponseBody().write(response.getBytes());
			exchange.getResponseBody().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
