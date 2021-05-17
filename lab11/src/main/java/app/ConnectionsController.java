package app;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import social.Person;
import social.PersonRepository;

@RestController
@RequestMapping("/connections")
public class ConnectionsController {
	private PersonRepository repository = new PersonRepository();

	@GetMapping("/least")
	public List<Person> getLeastConnected(@RequestParam int k) {
		return repository.getLeastConnected(k);
	}

	@GetMapping("/most")
	public List<Person> getMostConnected(@RequestParam int k) {
		return repository.getMostConnected(k);
	}
}
