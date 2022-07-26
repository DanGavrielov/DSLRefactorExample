package ext_lib.schedule;

import java.time.LocalDateTime;

public class Event {
    private String name;
    private LocalDateTime dateTime = LocalDateTime.now();

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
