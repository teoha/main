package seedu.address.model.itinerary.day;

import seedu.address.model.itinerary.Description;
import seedu.address.model.itinerary.Expenditure;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;
import seedu.address.model.itinerary.day.exceptions.CompulsoryFieldEmptyException;
import seedu.address.model.itinerary.event.EventList;

import java.time.LocalDateTime;
import java.util.Optional;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Day {
    private final Name name;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Description description;
    private final Location destination;
    private final Expenditure totalBudget;
    private final EventList eventList;

    public Day (Name name, LocalDateTime startDate, LocalDateTime endDate, Description description
            , Location destination, Expenditure totalBudget, EventList eventList) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.destination = destination;
        this.totalBudget = totalBudget;
        this.eventList = eventList;
    }

    private Day (Builder builder) {
        try {
            requireAllNonNull(builder.name, builder.startDate, builder.endDate);
        } catch (NullPointerException e) {
            throw new CompulsoryFieldEmptyException();
        }
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.description = builder.description;
        this.destination = builder.destination;
        this.totalBudget = builder.totalBudget;
        this.eventList = builder.eventList;
    }


    // Compulsory Field getters
    public Name getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }


    // Optional field getters
    public Optional<Description> getDescription() {
        return Optional.of(description);
    }

    public Optional<Location> getDestination() {
        return Optional.of(destination);
    }

    public Optional<Expenditure> getTotalBudget() {
        return Optional.of(totalBudget);
    }

    public Optional<EventList> getEventList() {
        return Optional.of(eventList);
    }

    public boolean isSameDay(Day otherDay) {
        if (otherDay == this) {
            return true;
        } else {
            return otherDay.getName().equals(getName())
                    && otherDay.getStartDate().equals(getStartDate())
                    && otherDay.getEndDate().equals(getEndDate())
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
                && otherDay.getStartDate().equals(getStartDate())
                && otherDay.getEndDate().equals(getEndDate())
                && otherDay.getDescription().equals(getDescription())
                && otherDay.getDestination().equals(getDestination())
                && otherDay.getEventList().equals(getEventList());
    }

    public boolean isClashingWith(Day other) {
        return (this.getStartDate().compareTo(other.getEndDate()) == -1 && this.getEndDate().compareTo(other.getStartDate()) == 1)
                || (this.getEndDate().compareTo(other.getStartDate()) == -1 && this.getStartDate().compareTo(other.getEndDate()) == 1);

    }

    private static class Builder {
        private Name name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Description description;
        private Location destination;
        private Expenditure totalBudget;
        private EventList eventList;

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

        public Builder setDescription (Description description){
            this.description = description;
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

        public Builder setEventList (EventList eventList) {
            this.eventList = eventList;
            return this;
        }

        public Day build(){
            return new Day(this);
        }

    }

}
