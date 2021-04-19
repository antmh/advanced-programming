package lab8.model;

public class Director extends Role {
	private Movie movieDirected;

	public Movie getMovieDirected() {
		return movieDirected;
	}

	public void setMovieDirected(Movie movieDirected) {
		this.movieDirected = movieDirected;
	}
}
