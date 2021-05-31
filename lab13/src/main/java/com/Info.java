package com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Info extends Command {
    private Pattern pattern = Pattern.compile("\\s*info(\\s+(?<language>[^\\s]+)(\\s+(?<country>[^\\s]+))?)?\\s*");

    @Override
    public void execute(Matcher matcher) {
        var language = matcher.group("language");
        var country = matcher.group("country");
        if (language == null) {
            displayInfo(getLocale());
        } else if (country == null) {
            displayInfo(new Locale(language));
        } else {
            displayInfo(new Locale(language, country));
        }
    }

    private void displayInfo(Locale locale) {
        var pattern = getResourceBundle().getString("info");
        Object[] arguments = { locale.getDisplayName(getLocale()) };
        var message = new MessageFormat(pattern).format(arguments);
        System.out.println(message);
        System.out.println(locale.getDisplayCountry(getLocale()) + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println(locale.getDisplayLanguage(getLocale()) + " (" + locale.getDisplayLanguage(locale) + ")");
        var currency = Currency.getInstance(locale);
        System.out.println(currency.getCurrencyCode() + " (" + currency.getDisplayName(getLocale()) + ")");
        var dateFormatSymbols = DateFormatSymbols.getInstance(locale);
        for (var weekDay : dateFormatSymbols.getWeekdays()) {
            System.out.print(weekDay + " ");
        }
        System.out.println();
        for (var month : dateFormatSymbols.getMonths()) {
            System.out.print(month + " ");
        }
        System.out.println();
        System.out.println(DateFormat.getDateInstance(DateFormat.LONG, locale).format(new Date()));
    }

    @Override
    protected Pattern getPattern() {
        return pattern;
    }
}
