package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements Runnable {
	private String[] args;

	public App(String[] args) {
		this.args = args;
	}

	@Override
	public void run() {
		SpringApplication.run(App.class, args);
	}
}
