package seedu.address.ui.trips;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.trips.edit.CancelEditTripCommand;
import seedu.address.logic.commands.trips.edit.DoneEditTripCommand;
import seedu.address.logic.commands.trips.edit.EditTripFieldCommand;
import seedu.address.logic.parser.ParserDateUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.ui.MainWindow;
import seedu.address.ui.template.Page;
import seedu.address.ui.components.form.DateFormItem;
import seedu.address.ui.components.form.ExpenditureFormItem;
import seedu.address.ui.components.form.TextFormItem;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class EditTripPage extends Page<AnchorPane> {

    private static final String FXML = "trips/EditTripPage.fxml";

    private TextFormItem tripNameFormItem;
    private TextFormItem tripDestinationFormItem;
    private DateFormItem tripStartDateFormItem;
    private DateFormItem tripEndDateFormItem;
    private ExpenditureFormItem tripTotalBudgetFormItem;

    @FXML
    private VBox formItemsPlaceholder;

    @FXML
    private Button addButton;

    public EditTripPage(MainWindow mainWindow, Logic logic, Model model) {
        super(FXML, mainWindow, logic, model);
        initFormWithModel();
    }

    public void fillPage() {
        EditTripFieldCommand.EditTripDescriptor currentEditDescriptor
                = model.getPageStatus().getEditTripDescriptor();
        if (currentEditDescriptor == null) {
            return;
        }
        if (currentEditDescriptor.getName() != null) {
            tripNameFormItem.setValue(currentEditDescriptor.getName().toString());
        }
        if (currentEditDescriptor.getDestination() != null) {
            tripDestinationFormItem.setValue(currentEditDescriptor.getDestination().toString());
        }
        if (currentEditDescriptor.getStartDate() != null) {
            tripStartDateFormItem.setValue(currentEditDescriptor.getStartDate().toLocalDate());
        }
        if (currentEditDescriptor.getEndDate() != null) {
            tripEndDateFormItem.setValue(currentEditDescriptor.getEndDate().toLocalDate());
        }
        if (currentEditDescriptor.getBudget() != null) {
            tripTotalBudgetFormItem.setValue(currentEditDescriptor.getBudget());
        }
    }

    /**
     * Initialises and fills up all the placeholders of this window.
     */
    private void initFormWithModel() {
        //Initialise with new display data
        tripNameFormItem = new TextFormItem("Name of trip : ", nameFormValue -> {
            mainWindow.executeGuiCommand(
                    EditTripFieldCommand.COMMAND_WORD
                            + " " + PREFIX_NAME + nameFormValue);
                });
        tripStartDateFormItem = new DateFormItem("Start date : ",
                startDate -> {
                    mainWindow.executeGuiCommand(EditTripFieldCommand.COMMAND_WORD
                            + " " + PREFIX_DATE_START
                            + ParserDateUtil.getStringFromDate(startDate.atStartOfDay()));
                });
        tripEndDateFormItem = new DateFormItem("End date : ",
                endDate -> {
                    mainWindow.executeGuiCommand(EditTripFieldCommand.COMMAND_WORD
                            + " " + PREFIX_DATE_END
                            + ParserDateUtil.getStringFromDate(endDate.atStartOfDay()));
                });
        tripTotalBudgetFormItem = new ExpenditureFormItem("Total budget : ", totalBudget -> {
            mainWindow.executeGuiCommand(EditTripFieldCommand.COMMAND_WORD
                    + " " + PREFIX_BUDGET + totalBudget);
                });
        tripDestinationFormItem = new TextFormItem("Destination : ", destinationValue -> {
            mainWindow.executeGuiCommand(EditTripFieldCommand.COMMAND_WORD
                    + " " + PREFIX_LOCATION + destinationValue);
                });

        fillPage(); //update and overwrite with existing edit descriptor

        formItemsPlaceholder.getChildren().addAll(
                tripNameFormItem.getRoot(),
                tripStartDateFormItem.getRoot(),
                tripEndDateFormItem.getRoot(),
                tripTotalBudgetFormItem.getRoot(),
                tripDestinationFormItem.getRoot());
    }

    @FXML
    private void handleEditTripDone() {
        String commandText = DoneEditTripCommand.COMMAND_WORD;
        mainWindow.executeGuiCommand(commandText);
    }

    @FXML
    private void handleEditCancel() {
        String commandText = CancelEditTripCommand.COMMAND_WORD;
        mainWindow.executeGuiCommand(commandText);
    }
}
