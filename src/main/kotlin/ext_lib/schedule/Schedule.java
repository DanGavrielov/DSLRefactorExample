package ext_lib.schedule;

import java.util.ArrayList;

public class Schedule {
    private ArrayList<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void cancelEvent(Event event) {
        this.events.remove(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "events=" + events +
                '}';
    }
}
