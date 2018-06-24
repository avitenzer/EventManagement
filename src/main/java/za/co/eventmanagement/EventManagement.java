package za.co.eventmanagement;

import za.co.eventmanagement.domain.Event;
import za.co.eventmanagement.domain.Talk;
import za.co.eventmanagement.util.InvalidTalkFormat;
import za.co.eventmanagement.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class EventManagement {

    public static void main(String [] args) throws IOException {

        try {
            LinkedList<Talk> talks = new LinkedList<>();

            InputStream inputStream = EventManagement.class.getClassLoader().getResource("events.txt").openStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            for (String line; (line = reader.readLine()) != null; ) {
                talks.add(Parser.parseLine(line));
            }

            EventScheduler eventScheduler = new EventScheduler(talks);
            Event event = eventScheduler.scheduleTalks();
            event.printEvent();
        } catch (InvalidTalkFormat itf){
            System.out.println(" The file contain line with an invalid format "+itf.getMessage());
        }
    }
}
