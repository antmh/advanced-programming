package com;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetLocale extends Command {
    private Pattern pattern = Pattern.compile("\\s*set\\s+(?<language>[^\\s]+)(\\s+(?<country>[^\\s]+))?\\s*");

    @Override
    public void execute(Matcher matcher) {
        var language = matcher.group("language");
        var country = matcher.group("country");
        if (country != null) {
            setLocale(language, country);
        } else {
            setLocale(language);
        }
        var pattern = getResourceBundle().getString("locale.set");
        Object[] arguments = { getLocale().getDisplayName(getLocale()) };
        var message = new MessageFormat(pattern).format(arguments);
        System.out.println(message);
    }

    @Override
    protected Pattern getPattern() {
        return pattern;
    }
}
