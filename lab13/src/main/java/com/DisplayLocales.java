package com;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayLocales extends Command {
    private Pattern pattern = Pattern.compile("\\s*locales\\s*");
    
    @Override
    public void execute(Matcher matcher) {
        System.out.println(getResourceBundle().getString("locales"));
        for (var locale : Locale.getAvailableLocales()) {
            System.out.println(locale.getDisplayName(getLocale()));
        }
    }

    @Override
    protected Pattern getPattern() {
        return pattern;
    }
}
