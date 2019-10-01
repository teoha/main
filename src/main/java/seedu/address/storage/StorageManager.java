package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTravelPal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TravelPal data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TravelPalStorage travelPalStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(TravelPalStorage travelPalStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.travelPalStorage = travelPalStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TravelPal methods ==============================

    @Override
    public Path getTravelPalFilePath() {
        return travelPalStorage.getTravelPalFilePath();
    }

    @Override
    public Optional<ReadOnlyTravelPal> readTravelPal() throws DataConversionException, IOException {
        return readTravelPal(travelPalStorage.getTravelPalFilePath());
    }

    @Override
    public Optional<ReadOnlyTravelPal> readTravelPal(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return travelPalStorage.readTravelPal(filePath);
    }

    @Override
    public void saveTravelPal(ReadOnlyTravelPal travelPal) throws IOException {
        saveTravelPal(travelPal, travelPalStorage.getTravelPalFilePath());
    }

    @Override
    public void saveTravelPal(ReadOnlyTravelPal travelPal, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        travelPalStorage.saveTravelPal(travelPal, filePath);
    }

}
