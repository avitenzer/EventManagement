package za.co.eventmanagement;

import junit.framework.Assert;
import org.junit.Test;
import za.co.eventmanagement.domain.Talk;
import za.co.eventmanagement.util.InvalidTalkFormat;
import za.co.eventmanagement.util.Parser;

public class TestParser {

    @Test
    public void testParser() throws InvalidTalkFormat {

        String line = "Common Ruby Errors 45min";

        Talk talk = Parser.parseLine(line);

        Assert.assertTrue(talk.getTitle().equals(line));
        Assert.assertTrue(talk.getTimeInMinutes() == 45);

    }


    @Test
    public void testParserWhenLighting() throws InvalidTalkFormat {

        String line = "Rails for Python Developers lightning";

        Talk talk = Parser.parseLine(line);

        Assert.assertTrue(talk.getTitle().equals(line));
        Assert.assertTrue(talk.getTimeInMinutes() == 5);

    }

    @Test
    public void testInvalidFormat(){

        String line = "Rails for Python Developers 45";

        Talk talk = null;
        try {
            talk = Parser.parseLine(line);
        } catch (InvalidTalkFormat invalidTalkFormat) {
            Assert.assertTrue(invalidTalkFormat.getMessage().equals("The following line doesn't have time: Rails for Python Developers 45"));
        }



    }






}
