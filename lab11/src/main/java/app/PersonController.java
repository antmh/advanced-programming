package app;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.FriendAlreadyAddedException;
import exceptions.PersonAlreadyExistsException;
import exceptions.PersonNotFoundException;
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

	@GetMapping("/{name}")
	public Person get(@PathVariable String name) throws PersonNotFoundException {
		return repository.findByName(name);
	}

	@PostMapping
	public void add(@RequestBody Person person) throws PersonAlreadyExistsException {
		repository.create(person);
	}

	@PutMapping("/{oldName}")
	public void changeName(@PathVariable String oldName, @RequestBody String name)
			throws PersonNotFoundException, PersonAlreadyExistsException {
		repository.updateName(oldName, name);
	}

	@DeleteMapping("/{name}")
	public void delete(@PathVariable String name) throws PersonNotFoundException {
		repository.delete(name);
	}

	@GetMapping("/{name}/friends")
	public Set<String> getFriends(@PathVariable String name) throws PersonNotFoundException {
		return repository.findByName(name).getFriends();
	}

	@PostMapping("/{name}/friends")
	public void addFriend(@PathVariable String name, @RequestBody String friendName)
			throws PersonNotFoundException, FriendAlreadyAddedException {
		repository.addFriend(name, friendName);
	}

	@PostMapping("/{name}/messages")
	public void sendMessage(@PathVariable String name, @RequestBody String message) throws PersonNotFoundException {
		repository.addMessage(name, message);
	}
}
