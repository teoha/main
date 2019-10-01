package seedu.address.model.itinerary.day;

import seedu.address.model.itinerary.*;
import seedu.address.model.itinerary.event.EventList;

public class Day {
    private final Name name;
    private final Date from;
    private final Date to;
    private final Description description;
    private final Location destination;
    private final Expenditure totalBudget;
    private final EventList eventList;

    public Day(Name name, Date from, Date to, Description description
            , Location destination, Expenditure totalBudget, DayList dayList, EventList eventList) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.description = description;
        this.destination = destination;
        this.totalBudget = totalBudget;
        this.eventList = eventList;
    }

    public Name getName() {
        return name;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
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
                    && otherDay.getFrom().equals(getFrom())
                    && otherDay.getTo().equals(getTo())
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
                && otherDay.getFrom().equals(getFrom())
                && otherDay.getTo().equals(getTo())
                && otherDay.getDescription().equals(getDescription())
                && otherDay.getDestination().equals(getDestination())
                && otherDay.getEventList().equals(getEventList());
    }

    public boolean isClashingWith(Day other) {
        return (this.getFrom().compareTo(other.getTo()) == -1 && this.getTo().compareTo(other.getFrom()) == 1)
                || (this.getTo().compareTo(other.getFrom()) == -1 && this.getFrom().compareTo(other.getTo()) == 1);

    }
}
