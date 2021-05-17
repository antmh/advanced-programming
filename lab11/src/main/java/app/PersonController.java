package app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import social.Person;
import social.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {
	private PersonRepository repository = new PersonRepository();

	@GetMapping
	public List<Person> getAll() {
		return repository.findAll();
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody Person person) {
		if (repository.create(person)) {
			return new ResponseEntity<>("Person added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Person already exists", HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{oldName}")
	public ResponseEntity<String> changeName(@PathVariable String oldName, @RequestBody String name) {
		if (repository.updateName(oldName, name)) {
			return new ResponseEntity<>("Person name changed", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Person not found", HttpStatus.GONE);
		}
	}

	@DeleteMapping("/{name}")
	public ResponseEntity<String> delete(@PathVariable String name) {
		if (repository.delete(name)) {
			return new ResponseEntity<>("Person deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Person not found", HttpStatus.GONE);
		}
	}

	@GetMapping("/{name}/friends")
	public ResponseEntity<Set<Person>> getFriends(@PathVariable String name) {
		var person = repository.findByName(name);
		if (person.isPresent()) {
			return new ResponseEntity<>(person.get().getFriends(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new HashSet<>(), HttpStatus.GONE);
		}
	}

	@PostMapping("/{name}/friends")
	public ResponseEntity<String> addFriend(@PathVariable String name, @RequestBody String friendName) {
		if (repository.addFriend(name, friendName)) {
			return new ResponseEntity<>("Friend added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Could not add friend", HttpStatus.BAD_REQUEST);
		}
	}
}
