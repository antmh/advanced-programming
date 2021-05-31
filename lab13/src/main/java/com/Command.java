package com;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    private static Locale locale = new Locale("en", "GB");

    public boolean executeIfMatches(String input) {
        var matcher = getPattern().matcher(input);
        if (!matcher.matches()) {
            return false;
        }
        execute(matcher);
        return true;
    }

    public abstract void execute(Matcher matcher);

    protected abstract Pattern getPattern();

    protected static Locale getLocale() {
        return locale;
    }

    protected static void setLocale(String language) {
        locale = new Locale(language);
    }

    protected static void setLocale(String language, String country) {
        locale = new Locale(language, country);
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("res.Messages", locale);
    }
}
