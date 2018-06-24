package za.co.eventmanagement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.eventmanagement.domain.Event;
import za.co.eventmanagement.domain.Talk;
import za.co.eventmanagement.util.InvalidTalkFormat;
import za.co.eventmanagement.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestEventScheduler {


    private List<String> fileContent = null;

    @Before
    public void setUp() throws IOException {
        getListOfTalks();
    }

    private void getListOfTalks() throws IOException {

        fileContent = new ArrayList();

        InputStream inputStream =  EventManagement.class.getClassLoader().getResource("events.txt").openStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        for (String line; (line = reader.readLine()) != null;) {
            fileContent.add(line );
        }
    }

    private List<String> getTitles() throws InvalidTalkFormat {

        LinkedList<Talk> talks = new LinkedList<>();

        for (String str: fileContent) {
            talks.add( Parser.parseLine(str));
        }

        EventScheduler eventScheduler = new EventScheduler(talks);

        Event event = eventScheduler.scheduleTalks();

        return event.asListOfStrings();
    }

    @Test
    public void testLunchExists() throws InvalidTalkFormat {

        List<String> titles = getTitles();

        long lunchOccurances = titles.stream().filter(s -> s.contains("Lunch")).count();

        Assert.assertTrue(lunchOccurances == 2);
    }

    @Test
    public void testNetworkingEventExists() throws InvalidTalkFormat {

        List<String> titles = getTitles();

        long lunchOccurances = titles.stream().filter(s -> s.contains("Networking Event")).count();

        Assert.assertTrue(lunchOccurances == 2);
    }


    @Test
    public void testAllTalksAddedToEvent() throws IOException, InvalidTalkFormat {

        List<String> titles = getTitles();

        Assert.assertTrue((titles.size() - 4) == fileContent.size());
    }


}
