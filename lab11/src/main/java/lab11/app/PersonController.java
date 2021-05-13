package lab11.app;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import social.Person;
import social.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {
	private PersonRepository repository = new PersonRepository();
	
	@GetMapping("/all")
	public List<Person> getAll() {
		return repository.findAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Person person) {
		if (repository.create(person)) {
			return new ResponseEntity<>("Person added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Person already exists", HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/name/{oldName}")
	public ResponseEntity<String> changeName(@PathVariable String oldName, @RequestParam String name) {
		if (repository.updateName(oldName, name)) {
			return new ResponseEntity<>("Person name changed", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Person not found", HttpStatus.GONE);
		}
	}
	
	@DeleteMapping("/name/{name}")
	public ResponseEntity<String> delete(@PathVariable String name) {
		if (repository.delete(name)) {
			return new ResponseEntity<>("Person deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Person not found", HttpStatus.GONE);
		}
	}
}
