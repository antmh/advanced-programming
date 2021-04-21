package lab8.wikidata;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

import com.bordercloud.sparql.SparqlClient;
import com.bordercloud.sparql.SparqlClientException;
import com.bordercloud.sparql.SparqlResult;

public class Client {
	private URI endpointUrl;
	private SparqlClient client;
	private Pattern idPattern;

	public Client() {
		try {
			endpointUrl = new URI("https://query.wikidata.org/sparql");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		client = new SparqlClient(false);
		client.setEndpointRead(endpointUrl);
		idPattern = Pattern.compile("Q\\d+");
	}

	public List<Movie> findMovies(String name) {
		name = StringEscapeUtils.escapeJava(name);
		SparqlResult result = null;
		try {
			result = client.query(
					"SELECT DISTINCT ?item ?itemDescription WHERE { "
					+ "?item wdt:P31 wd:Q11424; " // is film
					+ "wdt:P1476 ?name. "
					+ "FILTER(REGEX(?name, \"^" + name + "$\", \"i\")) "
					+ "SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". } "
					+ "}");
		} catch (SparqlClientException e) {
			e.printStackTrace();
		}
		var model = result.getModel();
		List<Movie> movies = new ArrayList<>();
		for (var row : model.getRows()) {
			var movie = new Movie();
			var matcher = idPattern.matcher(row.get("item").toString());
			matcher.find();
			movie.setId(matcher.group());
			movie.setDescription(row.get("itemDescription").toString());
			movies.add(movie);
		}
		return movies;
	}
}
