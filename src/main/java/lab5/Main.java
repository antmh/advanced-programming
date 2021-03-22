package lab5;

import java.util.Scanner;

import lab5.commands.AddCommand;
import lab5.commands.Command;
import lab5.commands.ListCommand;
import lab5.commands.LoadCommand;
import lab5.commands.PlayCommand;
import lab5.commands.ReportCommand;
import lab5.commands.SaveCommand;
import lab5.items.MovieFactory;
import lab5.items.SongFactory;

public class Main {
    public static void main(String[] args) {
        var catalog = new Catalog();
        var scanner = new Scanner(System.in);
        Command[] commands = { 
                new AddCommand(new MovieFactory(), new SongFactory()),
                new ListCommand(),
                new LoadCommand(),
                new PlayCommand(),
                new SaveCommand(),
                new ReportCommand()
        };
        mainLoop: while (true) {
            var input = scanner.nextLine().trim();
            if (input.equals("quit")) {
                break;
            }
            for (var command : commands) {
                try {
                    if (command.execute(catalog, input)) {
                        continue mainLoop;
                    }
                } catch (CatalogException e) {
                    System.err.println(e.getMessage());
                }
            }
            System.err.println("Unknown command");
        }
        scanner.close();
    }
}
