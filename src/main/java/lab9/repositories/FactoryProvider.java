package lab9.repositories;

import java.io.IOException;
import java.io.InputStream;

import lab9.repositories.jdbc.JDBCFactory;
import lab9.repositories.jpa.JPAFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class FactoryProvider {
	static public AbstractFactory createFactory() {
		InputStream config = FactoryProvider.class.getResourceAsStream("config.json");
		JSONObject json = null;
		try {
			json = new JSONObject(new String(config.readAllBytes()));
		} catch (IOException e) {
			System.err.println("Could not read configuration file");
			e.printStackTrace();
		}
		var implementation = json.get("implementation");
		if (implementation.equals("jdbc")) {
			return new JDBCFactory();
		} else if (implementation.equals("jpa")) {
			return new JPAFactory();
		}
		throw new JSONException("implementation key must have jdbc or jpa as value");
	}
}
