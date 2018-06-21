package za.co.eventmanagement;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import za.co.eventmanagement.domain.Event;
import za.co.eventmanagement.domain.Talk;
import za.co.eventmanagement.domain.Track;

import java.util.Collections;
import java.util.LinkedList;

public class EventScheduler {

    LinkedList<Talk> talks;

    public EventScheduler(LinkedList<Talk> talks) {
        this.talks = talks;
    }

    public Event scheduleTalks() {

        boolean solutionFound = false;

        Event event = new Event();

        DateTimeFormatter dateFormat =  DateTimeFormat.forPattern("HH:mm a");

        LinkedList<Talk> tempTalks = new LinkedList<Talk>(talks);

        while (!solutionFound) {

            Track track = new Track("Track 1");

            event.getTracks().add(track);

            DateTime dateTime = getStartOfDay();

            boolean completed = true;

            while (talks.size() > 0) {

                if (dateTime.getHourOfDay() == 12) {

                    Talk lunch = new Talk("Lunch", 60, dateFormat.print(dateTime) );

                    track.getTalks().add(lunch);

                    dateTime = dateTime.plusMinutes(60);

                } else if ((dateTime.plusMinutes(talks.peekFirst().getTimeInMinutes())).isAfter( getEndOfDay() )) {

                    Talk networking = getNetworkingEvent();

                    track.getTalks().add(networking);

                    track = new Track("Track 2");

                    event.getTracks().add(track);

                    dateTime = getStartOfDay();

                } else {

                    if ((dateTime.getHourOfDay() == 11) && (dateTime.plusMinutes(talks.peekFirst().getTimeInMinutes())).isAfter(getLunchTime())) {
                        completed = false;
                        break;
                    } else {

                        Talk currentTalk = talks.removeFirst();

                        currentTalk.setStartTime(dateFormat.print(dateTime));

                        track.getTalks().add(currentTalk);

                        dateTime = dateTime.plusMinutes(currentTalk.getTimeInMinutes());
                    }
                }
            }

            if (completed) {

                Talk networking = getNetworkingEvent();
                track.getTalks().add(networking);
                solutionFound = true;
            } else {

                talks = new LinkedList<>(tempTalks);
                Collections.shuffle(talks);
                event = new Event();
            }
        }

        return event;
    }

    private Talk getNetworkingEvent() {
        return new Talk("Networking Event", 0, "05:00 PM");
    }

    private DateTime getStartOfDay(){
        return new DateTime().withHourOfDay(9).withMinuteOfHour(0);
    }

    private DateTime getEndOfDay(){
        return new DateTime().withHourOfDay(17).withMinuteOfHour(0);
    }

    private DateTime getLunchTime(){
        return new DateTime().withHourOfDay(12).withMinuteOfHour(0);
    }

}
