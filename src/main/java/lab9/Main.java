package lab9;

import lab9.repositories.MovieRepository;

public class Main {
	public static void main(String[] args) {
		System.out.println(new MovieRepository().findByTitle("title").get(0).getTitle());
		MovieManagerFactory.getInstance().close();
	}
}