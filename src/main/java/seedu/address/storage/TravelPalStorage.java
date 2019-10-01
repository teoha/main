package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTravelPal;
import seedu.address.model.TravelPal;

/**
 * Represents a storage for {@link TravelPal}.
 */
public interface TravelPalStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTravelPalFilePath();

    /**
     * Returns TravelPal data as a {@link ReadOnlyTravelPal}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTravelPal> readTravelPal() throws DataConversionException, IOException;

    /**
     * @see #getTravelPalFilePath()
     */
    Optional<ReadOnlyTravelPal> readTravelPal(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTravelPal} to the storage.
     * @param travelPal cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTravelPal(ReadOnlyTravelPal travelPal) throws IOException;

    /**
     * @see #saveTravelPal(ReadOnlyTravelPal)
     */
    void saveTravelPal(ReadOnlyTravelPal travelPal, Path filePath) throws IOException;

}
