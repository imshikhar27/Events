package in.shikhar.events;

public class EventModel {
    int img;
    String club_name;
    String event_name;
    String event_description;

    public EventModel(int img, String club_name, String event_name, String event_description) {
        this.img = img;
        this.club_name = club_name;
        this.event_name = event_name;
        this.event_description = event_description;
    }
}
