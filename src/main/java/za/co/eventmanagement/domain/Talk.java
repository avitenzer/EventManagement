package za.co.eventmanagement.domain;

public class Talk {

    private String title;
    private int timeInMinutes;
    private String startTime;

    public Talk() {
    }

    public Talk(String title, int timeInMinutes, String startTime) {
        this.title = title;
        this.timeInMinutes = timeInMinutes;
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return startTime+" "+title;
    }
}
