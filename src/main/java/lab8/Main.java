package lab8;

import java.util.NoSuchElementException;
import java.util.Scanner;

import lab8.wikidata.Client;

public class Main {
	public static void main(String[] args) {
		var client = new Client();
		var scanner = new Scanner(System.in);
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
			while (true) {
				int choice;
				try {
					choice = Integer.parseInt(scanner.next());
				} catch (NumberFormatException e) {
					choice = -1;
				}
				if (choice >= 0 && choice < movies.size()) {
					System.out.println(choice);
					break;
				} else {
					System.out.println("Invalid");
				}
			}
		}
		scanner.close();
	}
}