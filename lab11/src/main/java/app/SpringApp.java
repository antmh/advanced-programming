package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApp implements Runnable {
	private String[] args;

	public SpringApp(String[] args) {
		this.args = args;
	}

	@Override
	public void run() {
		SpringApplication.run(SpringApp.class, args);
	}
}
