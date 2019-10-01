package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.itinerary.Expenditure;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;
import seedu.address.model.itinerary.event.Event;

import java.time.LocalDateTime;


public class JsonAdaptedEvent {
        public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

        private final String name;
        private final LocalDateTime startTime;
        private final LocalDateTime endTime;
        private final String destination;
        //private final Booking booking;
        private final double totalBudget;
        //private final Inventory inventory;

        @JsonCreator
        public JsonAdaptedEvent(
                @JsonProperty("name") String name
                , @JsonProperty("startTime")LocalDateTime from
                , @JsonProperty("endTime") LocalDateTime to
                , @JsonProperty("destination")String destination
                , @JsonProperty("totalBudget")double totalBudget
        ) {
            this.name = name;
            this.startTime = from;
            this.endTime = to;
            this.destination = destination;
            this.totalBudget = totalBudget;
        }

        public JsonAdaptedEvent(Event source){
            this.name = source.getName().fullName;
            this.startTime = source.getStartTime();
            this.endTime = source.getEndTime();
            this.destination = source.getDestination().value;
            this.totalBudget = source.getTotalBudget().value;
        }

        public Event toModelType() throws IllegalValueException {
            if(name == null){
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
            }

            if(!Name.isValidName(name)){
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }

            final Name modelName = new Name(name);

            if(startTime == null){
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
            }

            // Assumes validation done upon creation

            final LocalDateTime modelStartTime = startTime;

            if (endTime == null){
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
            }

            // Assumes validation done upon creation

            final LocalDateTime modelEndTime = endTime;

            if (destination == null){
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
            }

            if (!Location.isValidAddress(destination)){
                throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
            }

            final Location modelDestination = new Location(destination);

            //No check for TotalBudget (defaults endTime 0)

            final Expenditure modelTotalBudget = new Expenditure(totalBudget);


            return new Event(modelName, modelStartTime,  modelEndTime, modelTotalBudget, modelDestination);
        }
}


