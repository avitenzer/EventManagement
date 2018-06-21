package za.co.eventmanagement;

import junit.framework.Assert;
import org.junit.Test;
import za.co.eventmanagement.domain.Talk;
import za.co.eventmanagement.util.Parser;

public class TestParser {

    @Test
    public void testParser(){

        String line = "Common Ruby Errors 45min";

        Talk talk = Parser.parseLine(line);

        Assert.assertTrue(talk.getTitle().equals(line));
        Assert.assertTrue(talk.getTimeInMinutes() == 45);

    }


    @Test
    public void testParserWhenLighting(){

        String line = "Rails for Python Developers lightning";

        Talk talk = Parser.parseLine(line);

        Assert.assertTrue(talk.getTitle().equals(line));
        Assert.assertTrue(talk.getTimeInMinutes() == 5);

    }






}
