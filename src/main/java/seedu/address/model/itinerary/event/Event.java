package seedu.address.model.itinerary.event;

import seedu.address.model.booking.Booking;
import seedu.address.model.inventory.Inventory;
import seedu.address.model.itinerary.Expenditure;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;

import java.time.LocalDateTime;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Event {
    private final Name name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Booking booking;
    private final Location destination;
    private final Expenditure totalBudget;
    private final Inventory inventory;

    public Event(Name name, LocalDateTime startTime, LocalDateTime endTime, Booking booking, Expenditure totalBudget, Inventory inventory, Location destination) {
        requireAllNonNull(name, startTime, endTime, booking, totalBudget, inventory);
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.booking = booking;
        this.destination = destination;
        this.totalBudget = totalBudget;
        this.inventory = inventory;
    }

    // temporary constructor until we implement booking and inventory, accepts null for now
    public Event(Name name, LocalDateTime startTime, LocalDateTime endTime, Expenditure totalBudget, Location destination) {
        requireAllNonNull(name, startTime, endTime, totalBudget);
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.booking = null;
        this.destination = destination;
        this.totalBudget = totalBudget;
        this.inventory = null;
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

    public Booking getBooking() {
        return booking;
    }

    public Expenditure getTotalBudget() {
        return totalBudget;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Location getDestination() {
        return destination;
    }

    /**
     * Returns true if both {@link Event} contain the same booking and their endTime and startTime time are the same.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent){
        if (otherEvent == this) {
            return true;
        }
        return otherEvent != null
                && otherEvent.getBooking().equals(getBooking())
                && (otherEvent.getEndTime().equals(getEndTime()) || otherEvent.getStartTime().equals(getStartTime()));

    }

    public boolean isClashingWith(Event other){
        return (this.getStartTime().compareTo(other.getEndTime()) == -1 && this.getEndTime().compareTo(other.getStartTime()) == 1)
                || (this.getEndTime().compareTo(other.getStartTime()) == -1 && this.getStartTime().compareTo(other.getEndTime()) == 1);
    }
}
