package seedu.address.model.trip;

import seedu.address.logic.parser.ParserDateUtil;
import seedu.address.model.itinerary.Expenditure;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;
import seedu.address.model.itinerary.day.DayList;

import java.time.LocalDateTime;
import java.util.Optional;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Trip {
    private final Name name;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final TripId tripId;
    private final Location destination;
    private final Expenditure totalBudget;
    private final DayList dayList;

    public Trip (Name name, LocalDateTime startDate, LocalDateTime endDate,
                Location destination, Expenditure totalBudget, DayList dayList) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
        this.totalBudget = totalBudget;
        this.dayList = dayList;
        this.tripId = new TripId();
    }

    private Trip (Builder builder) {
        requireAllNonNull(builder.name, builder.startDate, builder.endDate);
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.destination = builder.destination;
        this.totalBudget = builder.totalBudget;
        this.dayList = builder.dayList;
        this.tripId = new TripId();

    }


    //Compulsory field getters
    public Name getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public TripId getTripId() {
        return tripId;
    }

    public Location getDestination() {
        return destination;
    }

    public DayList getDayList() {
        return dayList;
    }

    // Optional field getters
    public Optional<Expenditure> getBudget() {
        return Optional.ofNullable(totalBudget);
    }

    /**
     * Returns true if both {@link Trip} contain the same booking and their to and from time are the same.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        } else {
            return otherTrip.getName().equals(getName())
                    && otherTrip.getStartDate().equals(getStartDate())
                    && otherTrip.getEndDate().equals(getEndDate())
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
                && otherTrip.getStartDate().equals(getStartDate())
                && otherTrip.getEndDate().equals(getEndDate())
                && otherTrip.getDestination().equals(getDestination())
                && otherTrip.getDayList().equals(getDayList());
    }

    public boolean isClashingWith(Trip other){
        return (this.getStartDate().compareTo(other.getEndDate()) == -1
                && this.getEndDate().compareTo(other.getStartDate()) == 1)
                || (this.getEndDate().compareTo(other.getStartDate()) == -1
                        && this.getStartDate().compareTo(other.getEndDate()) == 1);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(name.toString())
                .append(" From: ")
                .append(ParserDateUtil.getDisplayTime(startDate))
                .append(" To: ")
                .append(ParserDateUtil.getDisplayTime(endDate))
                .append(" Destination: ")
                .append(destination.toString())
                .append(" Total Budget: ")
                .append(totalBudget.toString());

        return builder.toString();
    }

    private static class Builder {
        private Name name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Location destination;
        private Expenditure totalBudget;
        private DayList dayList;

        public static Builder newInstance (){
            return new Builder();
        }

        private Builder(){
        }

        public Builder setName(Name name){
            this.name = name;
            return this;
        }

        public Builder setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setLocation (Location location) {
            this.destination = location;
            return this;
        }

        public Builder setTotalBudget (Expenditure totalBudget){
            this.totalBudget = totalBudget;
            return this;
        }

        public Builder setDayList (DayList dayList) {
            this.dayList = dayList;
            return this;
        }

        public Trip build(){
            return new Trip(this);
        }

    }
}
