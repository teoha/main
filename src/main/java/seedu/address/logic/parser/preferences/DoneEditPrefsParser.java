package seedu.address.logic.parser.preferences;

import seedu.address.logic.commands.preferences.DoneEditPrefsCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the arguments into a {@code DoneEditPrefsCommand}.
 */
public class DoneEditPrefsParser implements Parser<DoneEditPrefsCommand> {
    @Override
    public DoneEditPrefsCommand parse(String userInput) throws ParseException {
        return new DoneEditPrefsCommand();
    }
}