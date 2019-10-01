package seedu.address.model.itinerary.trip;

import seedu.address.model.itinerary.ConsecutiveOccurrenceList;
import seedu.address.model.itinerary.trip.exceptions.ClashingTripException;
import seedu.address.model.itinerary.trip.exceptions.TripNotFoundException;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class TripList extends ConsecutiveOccurrenceList<Trip> {

    @Override
    public boolean contains(Trip toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTrip);
    }

    @Override
    public boolean containsClashing(Trip toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isClashingWith);
    }

    @Override
    public void add(Trip toAdd) {
        requireNonNull(toAdd);
        if(containsClashing(toAdd)){
            throw new ClashingTripException();
        }
        internalList.add(toAdd);
    }

    @Override
    public void set(Trip targetTrip, Trip editedTrip) {
        requireAllNonNull(targetTrip, editedTrip);
        int index = internalList.indexOf(targetTrip);
        if(index == -1){
            throw new TripNotFoundException();
        }

        if (!targetTrip.isClashingWith(editedTrip) && contains(editedTrip)) {
            throw new ClashingTripException();
        }

        internalList.set(index, editedTrip);
    }

    @Override
    public void remove(Trip toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TripNotFoundException();
        }
    }

    @Override
    public void set(List<Trip> occurrences) {
        requireAllNonNull(occurrences);
        if(!areConsecutive(occurrences)){
            throw new ClashingTripException();
        }
        internalList.setAll(occurrences);
    }

    @Override
    public boolean areConsecutive(List<Trip> occurrences) {
        for (int i = 0; i < occurrences.size() - 1; i++) {
            for (int j = i + 1; j < occurrences.size(); j++) {
                if (occurrences.get(i).isClashingWith(occurrences.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
