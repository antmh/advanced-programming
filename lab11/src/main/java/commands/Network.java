package commands;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import guru.nidi.graphviz.model.Graph;
import social.Person;

import static guru.nidi.graphviz.model.Factory.*;

public class Network {
	private boolean acceptingNewConnections;
	private RestTemplate restTemplate = new RestTemplate();
	private static final String BASE_URL = "http://localhost:8080/";

	public Network() {
		acceptingNewConnections = true;
	}

	public synchronized boolean register(Person person) {
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "people", person, String.class);
		return response.getStatusCode() == HttpStatus.OK;
	}

	public synchronized Optional<Person> findPerson(String name) {
		ResponseEntity<Person> response = restTemplate.getForEntity(BASE_URL + "people/" + name, Person.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return Optional.of(response.getBody());
		} else {
			return Optional.empty();
		}
	}

	public synchronized boolean addFriend(String person, String friend) {
		ResponseEntity<String> response = restTemplate.postForEntity(Network.BASE_URL + "people/" + person + "friends",
				friend, String.class);
		return response.getStatusCode() == HttpStatus.OK;
	}

	public synchronized void sendMessage(String person, String message) {
		restTemplate.postForEntity(Network.BASE_URL + "people/" + person + "/messages", message, String.class);
	}

	public synchronized boolean stopNewConnections() {
		System.out.println("Stopping");
		boolean changed = acceptingNewConnections;
		acceptingNewConnections = false;
		notifyAll();
		return changed;
	}

	public synchronized Graph constructGraph() {
		List<Person> response = restTemplate.getForObject(BASE_URL + "people", List.class);
		var graph = mutGraph();
		for (var person : response) {
			var node = node(person.getName());
			graph.add(node);
			for (var friend : person.getFriends()) {
				if (person.getName().compareTo(friend) < 0) {
					var link = node.link(to(node(friend)));
					graph.add(link);
				}
			}
		}
		return graph.toImmutable();
	}
}
