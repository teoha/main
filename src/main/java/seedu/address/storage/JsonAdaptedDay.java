package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.itinerary.Description;
import seedu.address.model.itinerary.Expenditure;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;
import seedu.address.model.itinerary.day.Day;
import seedu.address.model.itinerary.event.Event;
import seedu.address.model.itinerary.event.EventList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedDay {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Day's %s field is missing!";

    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String description;
    private final String destination;
    private final double totalBudget;
    private final List<JsonAdaptedEvent> eventList = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedDay(
            @JsonProperty("name") String name
            , @JsonProperty("startTime")LocalDateTime from
            , @JsonProperty("endTime") LocalDateTime to
            , @JsonProperty("destination")String destination
            , @JsonProperty("description") String description
            , @JsonProperty("totalBudget")double totalBudget
            , @JsonProperty("dayList")List<JsonAdaptedEvent> eventList
    ) {
        this.name = name;
        this.startTime = from;
        this.endTime = to;
        this.description = description;
        this.destination = destination;
        this.totalBudget = totalBudget;
        if(eventList != null) {
            this.eventList.addAll(eventList);
        }
    }

    public JsonAdaptedDay(Day source){
        this.name = source.getName().fullName;
        this.startTime = source.getStartTime();
        this.endTime = source.getEndTime();
        this.destination = source.getDestination().value;
        this.description = source.getDescription().description;
        this.totalBudget = source.getTotalBudget().value;
        this.eventList.addAll(source.getEventList()
                .asUnmodifiableObservableList()
                .stream().map(JsonAdaptedEvent::new)
                .collect(Collectors.toList())
        );
    }

    public Day toModelType() throws IllegalValueException{
        final List<Event> events = new ArrayList<>();
        for (JsonAdaptedEvent event : eventList){
            events.add(event.toModelType());
        }

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

        if (description == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }

        if (!Description.isValidDescription(description)){
            throw  new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }

        final Description modelDescription = new Description(description);

        //No check for TotalBudget (defaults endTime 0)

        final Expenditure modelTotalBudget = new Expenditure(totalBudget);

        EventList modelEventList = new EventList();
        modelEventList.set(events);

        return new Day(modelName, modelStartTime, modelEndTime, modelDescription, modelDestination, modelTotalBudget, modelEventList);
    }
}
