package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.itinerary.Expenditure;
import seedu.address.model.itinerary.Location;
import seedu.address.model.itinerary.Name;
import seedu.address.model.itinerary.day.Day;
import seedu.address.model.itinerary.day.DayList;
import seedu.address.model.itinerary.trip.Trip;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedTrip {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Trip's %s field is missing!";

    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String destination;
    private final double totalBudget;
    private final List<JsonAdaptedDay> dayList = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedTrip(
            @JsonProperty("name") String name
            , @JsonProperty("startTime")LocalDateTime startTime
            , @JsonProperty("endTime") LocalDateTime endTime
            , @JsonProperty("destination")String destination
            , @JsonProperty("totalBudget")double totalBudget
            , @JsonProperty("dayList")List<JsonAdaptedDay> dayList
    ) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.destination = destination;
        this.totalBudget = totalBudget;
        if(dayList != null) {
            this.dayList.addAll(dayList);
        }
    }

    public JsonAdaptedTrip(Trip source){
        this.name = source.getName().fullName;
        this.startTime = source.getStartTime();
        this.endTime = source.getEndTime();
        this.destination = source.getDestination().value;
        this.totalBudget = source.getTotalBudget().value;
        this.dayList.addAll(source.getDayList()
                .asUnmodifiableObservableList()
                .stream().map(JsonAdaptedDay::new)
                .collect(Collectors.toList())
        );
    }

    public Trip toModelType() throws IllegalValueException {
        final List<Day> days = new ArrayList<>();
        for (JsonAdaptedDay day : dayList){
            days.add(day.toModelType());
        }

        if(name == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if(!Name.isValidName(name)){
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);

        if(startTime == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, seedu.address.model.itinerary.Date.class.getSimpleName()));
        }

        // Assumes validation done upon creation

        final LocalDateTime modelFrom = startTime;

        if (endTime == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, seedu.address.model.itinerary.Date.class.getSimpleName()));
        }

        // Assumes validation done upon creation

        final LocalDateTime modelTo = endTime;

        if(destination == null){
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }

        if(!Location.isValidAddress(destination)){
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }

        final Location modelDestination = new Location(destination);

        //No check for TotalBudget (defaults endTime 0)

        final Expenditure modelTotalBudget = new Expenditure(totalBudget);

        DayList modelDayList = new DayList();
        modelDayList.set(days);

        return new Trip(modelName, modelFrom, modelTo, modelDestination, modelTotalBudget ,modelDayList);
    }
}
