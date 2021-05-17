package social;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "friends" })
@Entity
@Table(name = "PEOPLE")
@NamedQuery(name = "person.find", query = "SELECT p FROM Person p WHERE p.name = :name")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@ManyToMany
	@JoinTable(name = "FRIENDSHIPS", joinColumns = @JoinColumn(name = "FRIEND2_ID"), inverseJoinColumns = @JoinColumn(name = "FRIEND1_ID"))
	private Set<Person> friendsWithSmallerIds;

	@ManyToMany
	@JoinTable(name = "FRIENDSHIPS", joinColumns = @JoinColumn(name = "FRIEND1_ID"), inverseJoinColumns = @JoinColumn(name = "FRIEND2_ID"))
	private Set<Person> friendsWithBiggerIds;

	@OneToMany(mappedBy = "reciever")
	private Set<Message> messages;
	
	public Person() {
		this("");
	}

	public Person(String name) {
		friendsWithBiggerIds = new HashSet<>();
		friendsWithSmallerIds = new HashSet<>();
		this.name = name;
		messages = new HashSet<>();
	}

	public boolean addFriend(Person friend) {
		if (friend.id < this.id) {
			return this.friendsWithSmallerIds.add(friend);
		} else {
			return this.friendsWithBiggerIds.add(friend);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getFriends() {
		Set<Person> result = new HashSet<>();
		result.addAll(friendsWithSmallerIds);
		result.addAll(friendsWithBiggerIds);
		return result;
	}

	public Set<Message> getMessages() {
		return messages;
	}
}
