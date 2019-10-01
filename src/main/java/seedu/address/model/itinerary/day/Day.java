package seedu.address.model.itinerary.day;

import seedu.address.model.itinerary.*;
import seedu.address.model.itinerary.event.EventList;

import java.time.LocalDateTime;

public class Day {
    private final Name name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Description description;
    private final Location destination;
    private final Expenditure totalBudget;
    private final EventList eventList;

    public Day(Name name, LocalDateTime startTime, LocalDateTime endTime, Description description
            , Location destination, Expenditure totalBudget, EventList eventList) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.destination = destination;
        this.totalBudget = totalBudget;
        this.eventList = eventList;
    }

    public Name getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Description getDescription() {
        return description;
    }

    public Location getDestination() {
        return destination;
    }

    public Expenditure getTotalBudget() {
        return totalBudget;
    }


    public EventList getEventList() {
        return eventList;
    }

    public boolean isSameDay(Day otherDay) {
        if (otherDay == this) {
            return true;
        } else {
            return otherDay.getName().equals(getName())
                    && otherDay.getStartTime().equals(getStartTime())
                    && otherDay.getEndTime().equals(getEndTime())
                    && otherDay.getDestination().equals(getDestination());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Day)) {
            return false;
        }

        Day otherDay = (Day) other;
        return otherDay.getName().equals(getName())
                && otherDay.getStartTime().equals(getStartTime())
                && otherDay.getEndTime().equals(getEndTime())
                && otherDay.getDescription().equals(getDescription())
                && otherDay.getDestination().equals(getDestination())
                && otherDay.getEventList().equals(getEventList());
    }

    public boolean isClashingWith(Day other) {
        return (this.getStartTime().compareTo(other.getEndTime()) == -1 && this.getEndTime().compareTo(other.getStartTime()) == 1)
                || (this.getEndTime().compareTo(other.getStartTime()) == -1 && this.getStartTime().compareTo(other.getEndTime()) == 1);

    }
}
