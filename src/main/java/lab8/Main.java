package lab8;

import java.util.NoSuchElementException;
import java.util.Scanner;

import lab8.dao.GenreRepository;
import lab8.dao.MovieRepository;
import lab8.dao.PersonRepository;
import lab8.model.Actor;
import lab8.model.Director;
import lab8.wikidata.Client;
import lab8.wikidata.MovieDescription;

public class Main {
	static private Client client;
	static private MovieRepository movieRepository;
	static private PersonRepository personRepository;
	static private GenreRepository genreRepository;
	static private Scanner scanner;

	public static void main(String[] args) {
		client = new Client();
		movieRepository = new MovieRepository();
		personRepository = new PersonRepository();
		genreRepository = new GenreRepository();
		scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Movie name: ");
			String input;
			try {
				input = scanner.nextLine();
			} catch (NoSuchElementException e) {
				break;
			}
			if (input.equals("")) {
				break;
			}
			var movies = client.findMovies(input);
			for (int i = 0; i < movies.size(); ++i) {
				System.out.println(i + ") " + movies.get(i).getDescription());
			}
			if (movies.size() == 0) {
				System.out.println("Not found");
			} else {
			addToRepositories(movies.get(getChoice(movies.size())));
			}
		}
		scanner.close();
	}

	static private void addToRepositories(MovieDescription description) {
		var genres = client.getGenres(description);
		for (var genre : genres) {
			genreRepository.create(genre);
		}
		var movie = client.getMovie(description);
		movie.setGenres(genres);
		movieRepository.create(movie);
		var actors = client.getActors(description);
		for (var actor : actors) {
			var role = new Actor();
			role.setMovieActedIn(movie);
			actor.getRoles().add(role);
			personRepository.create(actor);
		}
		var directors = client.getDirectors(description);
		for (var director : directors) {
			var role = new Director();
			role.setMovieDirected(movie);
			director.getRoles().add(role);
			personRepository.create(director);
		}
	}
	
	static int getChoice(int numberOfChoices) {
		while (true) {
			int choice;
			try {
				choice = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				choice = -1;
			}
			if (choice >= 0 && choice < numberOfChoices) {
				return choice;
			} else {
				System.out.println("Invalid");
			}
		}
	}
}