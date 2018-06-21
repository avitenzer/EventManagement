package za.co.eventmanagement.domain;

import java.util.ArrayList;
import java.util.List;

public class Track {

    String trackTitle;
    List<Talk> talks;

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public Track(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public List<Talk> getTalks() {
        if(talks == null){
            talks = new ArrayList<>();
        }

        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }
}
