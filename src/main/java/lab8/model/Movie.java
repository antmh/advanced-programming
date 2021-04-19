package lab8.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
	private String title;
	private Date date;
	private int duration;
	private double score;
	private int id;
	private List<Genre> genres;

	public Movie() {
		genres = new ArrayList<>();
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
		if (title == null) {
			throw new IllegalArgumentException("title cannot be null");
		}
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
		if (score < 1 || score > 5) {
			throw new IllegalArgumentException("score must be between 1 and 5");
		}
		this.score = score;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}
