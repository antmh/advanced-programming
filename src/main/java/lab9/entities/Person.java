package lab9.entities;

import java.util.Date;
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
import javax.persistence.Table;

@Entity
@Table(name = "PEOPLE")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@ManyToMany
	@JoinTable(name = "ACTORS", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private Set<Movie> moviesActedIn;
	
	@ManyToMany
	@JoinTable(name = "DIRECTORS", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	private Set<Movie> moviesDirected;
	
	public Person() {
		moviesActedIn = new HashSet<>();
		moviesDirected = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<Movie> getMoviesActedIn() {
		return moviesActedIn;
	}

	public void setMoviesActedIn(Set<Movie> moviesActedIn) {
		this.moviesActedIn = moviesActedIn;
	}

	public Set<Movie> getMoviesDirected() {
		return moviesDirected;
	}

	public void setMoviesDirected(Set<Movie> moviesDirected) {
		this.moviesDirected = moviesDirected;
	}
}
