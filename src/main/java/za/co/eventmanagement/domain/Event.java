package za.co.eventmanagement.domain;

import java.util.ArrayList;
import java.util.List;

public class Event {

    List<Track> tracks;

    public List<Track> getTracks() {

        if(tracks == null){
            tracks = new ArrayList<>();
        }

        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<String> asListOfStrings(){

        List<String> results = new ArrayList<String>();

        tracks.stream().forEach( tr ->{

            tr.getTalks().stream().forEach( i-> results.add(i.getStartTime()+" "+i.getTitle()));
        });

        return results;
    }

    public void printEvent(){
        tracks.stream().forEach( tr ->{
            System.out.println("\n"+tr.getTrackTitle()+"\n");
            tr.getTalks().stream().forEach( talk -> System.out.println(talk.toString()));
        });
    }
}
