package seedu.address.model.itinerary.trip;

import seedu.address.model.itinerary.Expenditure;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;
import seedu.address.model.itinerary.day.DayList;

import java.time.LocalDateTime;

public class Trip {
    private final Name name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Location destination;
    private final Expenditure totalBudget;
    private final DayList dayList;

    public Trip(Name name, LocalDateTime startTime, LocalDateTime endTime, Location destination, Expenditure totalBudget, DayList dayList) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.destination = destination;
        this.totalBudget = totalBudget;
        this.dayList = dayList;
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

    public Location getDestination() {
        return destination;
    }

    public Expenditure getTotalBudget() {
        return totalBudget;
    }

    public DayList getDayList() {
        return dayList;
    }

    /**
     * Returns true if both {@link Trip} contain the same booking and their endTime and startTime time are the same.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        } else {
            return otherTrip.getName().equals(getName())
                    && otherTrip.getStartTime().equals(getStartTime())
                    && otherTrip.getEndTime().equals(getEndTime())
                    && otherTrip.getDestination().equals(getDestination());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Trip)) {
            return false;
        }

        Trip otherTrip = (Trip) other;
        return otherTrip.getName().equals(getName())
                && otherTrip.getStartTime().equals(getStartTime())
                && otherTrip.getEndTime().equals(getEndTime())
                && otherTrip.getDestination().equals(getDestination())
                && otherTrip.getDayList().equals(getDayList());
    }

    public boolean isClashingWith(Trip other){
        return (this.getStartTime().compareTo(other.getEndTime()) == -1 && this.getEndTime().compareTo(other.getStartTime()) == 1)
                || (this.getEndTime().compareTo(other.getStartTime()) == -1 && this.getStartTime().compareTo(other.getEndTime()) == 1);
    }

}
