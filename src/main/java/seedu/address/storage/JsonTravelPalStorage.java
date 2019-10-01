package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTravelPal;

/**
 * A class to access TravelPal data stored as a json file on the hard disk.
 */
public class JsonTravelPalStorage implements TravelPalStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTravelPalStorage.class);

    private Path filePath;

    public JsonTravelPalStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTravelPalFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTravelPal> readTravelPal() throws DataConversionException {
        return readTravelPal(filePath);
    }

    /**
     * Similar to {@link #readTravelPal()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTravelPal> readTravelPal(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTravelPal> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTravelPal.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTravelPal(ReadOnlyTravelPal travelPal) throws IOException {
        saveTravelPal(travelPal, filePath);
    }

    /**
     * Similar to {@link #saveTravelPal(ReadOnlyTravelPal)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTravelPal(ReadOnlyTravelPal travelPal, Path filePath) throws IOException {
        requireNonNull(travelPal);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTravelPal(travelPal), filePath);
    }

}
