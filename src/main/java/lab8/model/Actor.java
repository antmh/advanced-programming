package lab8.model;

public class Actor extends Role {
	private Movie movieActedIn;

	public Movie getMovieActedIn() {
		return movieActedIn;
	}

	public void setMovieActedIn(Movie movieActedIn) {
		this.movieActedIn = movieActedIn;
	}
}
