package lab9.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@NamedQuery(name = "movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id")
@NamedQuery(name = "movie.findByName", query = "SELECT m FROM Movie m WHERE m.title = :name")
@Entity
@Table(name = "MOVIES")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;

	@Column(name = "TITLE", nullable = false, unique = true)
	private String title;

	@Column(name = "RELEASE_DATE")
	private Date date;

	@Column(name = "DURATION")
	private int duration;

	@Column(name = "SCORE")
	private double score;

	@ManyToMany
	@JoinTable(name = "MOVIES_GENRES_ASSOC", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres;

	@OneToMany(mappedBy = "movie")
	private Set<ChartEntry> chartEntries;

	public Movie() {
		genres = new HashSet<>();
		chartEntries = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Set<ChartEntry> getChartEntries() {
		return chartEntries;
	}

	public void setChartEntries(Set<ChartEntry> chartEntries) {
		this.chartEntries = chartEntries;
	}
}
