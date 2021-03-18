package lab5;

import java.util.Optional;
import java.util.regex.Pattern;

public class Commands {
    public static Optional<Item> addCommand(String command) throws InaccessiblePathException {
        var addPattern = Pattern.compile("add (?<path>[^\\s]+) (?<name>[^\\s]+) (?<type>song|movie) (?<info>.+)");
        var songInfoPattern = Pattern.compile("(?<artist>[^\\s]+)");
        var movieInfoPattern = Pattern.compile("(?<director>[^\\s]+)");
        var matcher = addPattern.matcher(command);
        if (matcher.matches()) {
            var path = matcher.group("path");
            var name = matcher.group("name");
            var type = matcher.group("type");
            var info = matcher.group("info");
            if (type.equals("song")) {
                matcher = songInfoPattern.matcher(info);
                if (matcher.matches()) {
                    var artist = matcher.group("artist");
                    if (matcher.matches()) {
                        return Optional.of(new Song(name, path, artist));
                    }
                }
            } else if (type.equals("movie")) {
                matcher = movieInfoPattern.matcher(info);
                var director = matcher.group("director");
                if (matcher.matches()) {
                    if (matcher.matches()) {
                        return Optional.of(new Movie(name, path, director));
                    }
                }
            }
        }
        return Optional.empty();
    }

    public static boolean listCommand(String command) {
        return command.equals("list");
    }

    public static Optional<String> playCommand(String command) {
        var playPattern = Pattern.compile("play (?<name>[^\\s]+)");
        var matcher = playPattern.matcher(command);
        if (matcher.matches()) {
            var name = matcher.group("name");
            return Optional.of(name);
        }
        return Optional.empty();
    }

    public static Optional<String> saveCommand(String command) {
        var savePattern = Pattern.compile("save (?<name>[^\\s]+)");
        var matcher = savePattern.matcher(command);
        if (matcher.matches()) {
            var name = matcher.group("name");
            return Optional.of(name);
        }
        return Optional.empty();
    }

    public static Optional<String> loadCommand(String command) {
        var loadPattern = Pattern.compile("load (?<name>[^\\s]+)");
        var matcher = loadPattern.matcher(command);
        if (matcher.matches()) {
            var name = matcher.group("name");
            return Optional.of(name);
        }
        return Optional.empty();
    }

    public static boolean quitCommand(String command) {
        return command.equals("quit");
    }
}
