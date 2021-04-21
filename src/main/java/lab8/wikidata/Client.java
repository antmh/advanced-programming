package lab8.wikidata;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

import com.bordercloud.sparql.SparqlClient;
import com.bordercloud.sparql.SparqlClientException;
import com.bordercloud.sparql.SparqlResult;

import lab8.model.Genre;
import lab8.model.Movie;
import lab8.model.Person;

public class Client {
	private URI endpointUrl;
	private SparqlClient client;
	private Pattern idPattern;
	private DateFormat dateFormat;
	private String MOVIE_LIST_QUERY = "SELECT DISTINCT ?item ?itemDescription WHERE { "
			+ "  ?item wdt:P31 wd:Q11424; "
			+ "  wdt:P1476 ?name. "
			+ "  FILTER(REGEX(?name, \"^namePlaceholder$\", \"i\")) "
			+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". } "
			+ "}";
	private String MOVIE_DETAILS_QUERY = "SELECT DISTINCT ?itemLabel ?duration (MIN(?date) AS ?publicationDate) WHERE {"
			+ "  ?item wdt:P31 wd:Q11424. "
			+ "  OPTIONAL { ?item wdt:P2047 ?duration. } "
			+ "  OPTIONAL { ?item wdt:P577 ?date. } "
			+ "  VALUES ?item { "
			+ "    wd:idPlaceholder "
			+ "  } "
			+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". } "
			+ "} "
			+ "GROUP BY ?itemLabel ?duration";
	private String GENRE_QUERY = "SELECT DISTINCT ?genreLabel WHERE { "
			+ "  ?item wdt:P31 wd:Q11424; "
			+ "  wdt:P136 ?genre "
			+ "  VALUES ?item { "
			+ "    wd:idPlaceholder "
			+ "  } "
			+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". } "
			+ "}";
	private String DIRECTOR_QUERY = "SELECT DISTINCT ?directorLabel ?dateOfBirth WHERE { "
			+ "  ?item wdt:P31 wd:Q11424; "
			+ "    wdt:P57 ?director. "
			+ "  OPTIONAL { ?director wdt:P569 ?dateOfBirth. } "
			+ "  VALUES ?item { "
			+ "    wd:idPlaceholder "
			+ "  } "
			+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". } "
			+ "}";
	private String ACTOR_QUERY = "SELECT DISTINCT ?actorLabel ?dateOfBirth WHERE { "
			+ "  ?item wdt:P31 wd:Q11424; "
			+ "    wdt:P161 ?actor. "
			+ "  OPTIONAL { ?actor wdt:P569 ?dateOfBirth. } "
			+ "  VALUES ?item { "
			+ "    wd:Q151599 "
			+ "  } "
			+ "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". } "
			+ "}";

	public Client() {
		try {
			endpointUrl = new URI("https://query.wikidata.org/sparql");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		client = new SparqlClient(false);
		client.setEndpointRead(endpointUrl);
		idPattern = Pattern.compile("Q\\d+");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	public List<MovieDescription> findMovies(String name) {
		name = StringEscapeUtils.escapeJava(name);
		SparqlResult result = null;
		try {
			result = client.query(MOVIE_LIST_QUERY.replace("namePlaceholder", name));
		} catch (SparqlClientException e) {
			e.printStackTrace();
		}
		var model = result.getModel();
		List<MovieDescription> movies = new ArrayList<>();
		for (var row : model.getRows()) {
			var movie = new MovieDescription();
			var matcher = idPattern.matcher(row.get("item").toString());
			matcher.find();
			movie.setId(matcher.group());
			movie.setDescription(row.get("itemDescription").toString());
			movies.add(movie);
		}
		return movies;
	}

	public Movie getMovie(MovieDescription description) {
		SparqlResult result = null;
		try {
			result = client.query(MOVIE_DETAILS_QUERY.replace("idPlaceholder", description.getId()));
		} catch (SparqlClientException e) {
			e.printStackTrace();
		}
		var model = result.getModel();
		var row = model.getRows().get(0);
		var movie = new Movie();
		movie.setTitle((String) row.get("itemLabel"));
		try {
			movie.setDate(dateFormat.parse((String) row.get("publicationDate")));
		} catch (ParseException e) {
			movie.setDate(new Date());
		}
		try {
			movie.setDuration(Integer.parseInt((String) row.get("duration")));
		} catch (NumberFormatException e) {
		}
		return movie;
	}

	public List<Genre> getGenres(MovieDescription description) {
		SparqlResult result = null;
		try {
			result = client.query(GENRE_QUERY.replace("idPlaceholder", description.getId()));
		} catch (SparqlClientException e) {
			e.printStackTrace();
		}
		var model = result.getModel();
		List<Genre> genres = new ArrayList<>();
		for (var row : model.getRows()) {
			var genre = new Genre();
			genre.setName((String) row.get("genreLabel"));
			genres.add(genre);
		}
		return genres;
	}

	public List<Person> getDirectors(MovieDescription description) {
		SparqlResult result = null;
		try {
			result = client.query(DIRECTOR_QUERY.replace("idPlaceholder", description.getId()));
		} catch (SparqlClientException e) {
			e.printStackTrace();
		}
		var model = result.getModel();
		List<Person> directors = new ArrayList<>();
		for (var row : model.getRows()) {
			var person = new Person();
			person.setName((String) row.get("directorLabel"));
			try {
				person.setDateOfBirth(dateFormat.parse((String) row.get("dateOfBirth")));
			} catch (ParseException e) {
				person.setDateOfBirth(new Date());
			}
			directors.add(person);
		}
		return directors;
	}

	public List<Person> getActors(MovieDescription description) {
		SparqlResult result = null;
		try {
			result = client.query(ACTOR_QUERY.replace("idPlaceholder", description.getId()));
		} catch (SparqlClientException e) {
			e.printStackTrace();
		}
		var model = result.getModel();
		List<Person> actors = new ArrayList<>();
		for (var row : model.getRows()) {
			var person = new Person();
			person.setName((String) row.get("actorLabel"));
			try {
				person.setDateOfBirth(dateFormat.parse((String) row.get("dateOfBirth")));
			} catch (ParseException e) {
				person.setDateOfBirth(new Date());
			}
			actors.add(person);
		}
		return actors;
	}
}
