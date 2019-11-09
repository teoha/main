package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.Expense;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;
import seedu.address.model.itinerary.event.Event;

/**
 * Jackson friendly version of {@code Event}.
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String destination;
    //private final Optional<Booking> booking;
    private final Optional<JsonAdaptedExpense> expense;
    //private final Optional<Inventory> inventory;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name,
            @JsonProperty("startTime") LocalDateTime from,
            @JsonProperty("endTime") LocalDateTime to,
            @JsonProperty("destination") String destination,
            @JsonProperty("expense") Optional<JsonAdaptedExpense> expense
    //, @JsonProperty("booking")Optional<Booking> booking,
    // @JsonProperty("inventory")Optional<Inventory> inventory
    ) {
        this.name = name;
        this.startTime = from;
        this.endTime = to;
        this.destination = destination;
        this.expense = expense;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        this.name = source.getName().fullName;
        this.startTime = source.getStartDate();
        this.endTime = source.getEndDate();
        this.destination = source.getDestination().value;
        if (source.getExpense().isPresent()) {
            this.expense = Optional.of(new JsonAdaptedExpense(source.getExpense().get()));
        } else {
            this.expense = Optional.empty();
        }
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);

        if (startTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }

        // Assumes validation done upon creation

        final LocalDateTime modelStartTime = startTime;

        if (endTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }

        // Assumes validation done upon creation

        final LocalDateTime modelEndTime = endTime;

        if (destination == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }

        if (!Location.isValidLocation(destination)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }

        final Location modelDestination = new Location(destination);

        //No check for TotalBudget (defaults endTime 0)
        final Optional<Expense> modelExpense;

        if (expense.isPresent()) {

            modelExpense = Optional.of(expense.get().toModelType());
        } else {
            modelExpense = Optional.empty();
        }


        return new Event(modelName, modelStartTime, modelEndTime, modelExpense, modelDestination);
    }
}


