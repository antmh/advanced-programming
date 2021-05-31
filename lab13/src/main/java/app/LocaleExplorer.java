package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.Command;
import com.DisplayLocales;
import com.Info;
import com.SetLocale;

public class LocaleExplorer {
    public static void run() {
        Command commands[] = { new DisplayLocales(), new SetLocale(), new Info() };
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        OUTER_LOOP: while (true) {
            System.out.print(Command.getResourceBundle().getString("prompt"));
            String input = null;
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (input.equals("exit")) {
                break;
            }
            for (var command : commands) {
                if (command.executeIfMatches(input)) {
                    continue OUTER_LOOP;
                }
            }
            System.out.println(Command.getResourceBundle().getString("invalid"));
        }
    }
}
