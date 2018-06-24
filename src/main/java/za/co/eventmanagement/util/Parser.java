package za.co.eventmanagement.util;

import za.co.eventmanagement.domain.Talk;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final int LIGHTNING_TALK_TIME = 5;

    public static Talk parseLine(String line) throws InvalidTalkFormat {

        Talk talk = new Talk();

        int timeInMinutes = 0;

        Predicate<String> timeExist = str -> str.matches(".*\\d+.min");

        Predicate<String> isLightning = str -> str.contains("lightning");

        if (timeExist.test(line)) {

            timeInMinutes = getTimeInMinutes(line);

        } else if (isLightning.test(line)) {

            timeInMinutes = LIGHTNING_TALK_TIME;
        } else {
            throw new InvalidTalkFormat("The following line doesn't have time: "+line);
        }

        talk.setTitle(line);

        talk.setTimeInMinutes(timeInMinutes);

        return talk;

    }

    private static int getTimeInMinutes(String line) {
        int minutes = 0;

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            minutes = Integer.parseInt(matcher.group());
        }
        return minutes;
    }

}
