package social;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private String name;
	private Set<Person> friends;
	private Set<Message> messages;

	public Person() {
		friends = new HashSet<>();
		messages = new HashSet<>();
	}

	public Person(String name) {
		this();
		this.name = name;
	}

	public boolean addFriend(Person friend) {
		if (friends.add(friend)) {
			friend.friends.add(this);
			return true;
		}
		return false;
	}

	public void recieveMessage(Message message) {
		messages.add(message);
	}

	public void sendMessage(String content) {
		for (var friend : friends) {
			friend.recieveMessage(new Message(content, this));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getFriends() {
		return friends;
	}

	public Set<Message> getMessages() {
		return messages;
	}
}
