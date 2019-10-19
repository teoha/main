package seedu.address.model.appstatus;

import seedu.address.logic.commands.expenditure.edit.EditExpenditureFieldCommand;
import seedu.address.logic.commands.itinerary.days.edit.EditDayFieldCommand;
import seedu.address.logic.commands.itinerary.events.edit.EditEventFieldCommand;
import seedu.address.logic.commands.preferences.EditPrefsFieldCommand.EditPrefsDescriptor;
import seedu.address.logic.commands.trips.edit.EditTripFieldCommand.EditTripDescriptor;
import seedu.address.model.diary.DiaryEntry;
import seedu.address.model.diary.EditDiaryEntryDescriptor;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.itinerary.day.Day;
import seedu.address.model.itinerary.event.Event;
import seedu.address.model.trip.Trip;

/**
 * Abstraction of the page context of the TravelPal application.
 */
public class PageStatus {
    private final PageType pageType;
    private final Trip trip;
    private final Day day;
    private final Event event;
    private final Expenditure expenditure;
    private final DiaryEntry diaryEntry;
    private final EditTripDescriptor editTripDescriptor;
    private final EditPrefsDescriptor editPrefsDescriptor;
    private final EditDayFieldCommand.EditDayDescriptor editDayDescriptor;
    private final EditEventFieldCommand.EditEventDescriptor editEventDescriptor;
    private final EditExpenditureFieldCommand.EditExpenditureDescriptor editExpenditureDescriptor;
    private final EditDiaryEntryDescriptor editDiaryEntryDescriptor;

    public PageStatus(PageType pageType, Trip trip, Day day, Event event, DiaryEntry diaryEntry,
                      Expenditure expenditure, EditTripDescriptor editTripDescriptor,
                      EditPrefsDescriptor editPrefsDescriptor,
                      EditDayFieldCommand.EditDayDescriptor editDayDescriptor,
                      EditEventFieldCommand.EditEventDescriptor editEventDescriptor,
                      EditExpenditureFieldCommand.EditExpenditureDescriptor editExpenditureDescriptor,
                      EditDiaryEntryDescriptor editDiaryEntryDescriptor) {
        this.pageType = pageType;
        this.trip = trip;
        this.day = day;
        this.event = event;
        this.expenditure = expenditure;
        this.diaryEntry = diaryEntry;
        this.editTripDescriptor = editTripDescriptor;
        this.editPrefsDescriptor = editPrefsDescriptor;
        this.editDayDescriptor = editDayDescriptor;
        this.editEventDescriptor = editEventDescriptor;
        this.editExpenditureDescriptor = editExpenditureDescriptor;
        this.editDiaryEntryDescriptor = editDiaryEntryDescriptor;
    }

    /**
     * Immutable chained constructor.
     *
     * @param pageType page type to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewPageType(PageType pageType) {
        return new PageStatus(pageType, getTrip(), getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param trip trip to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewTrip(Trip trip) {
        return new PageStatus(getPageType(), trip, getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param day day to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewDay(Day day) {
        return new PageStatus(getPageType(), getTrip(), day, getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param event event to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewEvent(Event event) {
        return new PageStatus(getPageType(), getTrip(), getDay(), event, getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param expenditure expenditure to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewExpenditure(Expenditure expenditure) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), getDiaryEntry(), expenditure,
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    public PageStatus withResetTrip() {
        return withNewTrip(null);
    }

    public PageStatus withResetDay() {
        return withNewDay(null);
    }

    public PageStatus withResetEvent() {
        return withNewEvent(null);
    }

    public PageStatus withResetExpenditure() {
        return withNewExpenditure(null);
    }

    /**
     * Constructs a new {@link PageStatus} from the provided {@code diaryEntry}.
     *
     * @param diaryEntry {@link DiaryEntry} to use.
     * @return The new {@link PageStatus} with the provided {@code diaryEntry}.
     */
    public PageStatus withNewDiaryEntry(DiaryEntry diaryEntry) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), diaryEntry, getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param editTripDescriptor editTripDescriptor to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewEditTripDescriptor(EditTripDescriptor editTripDescriptor) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                editTripDescriptor, getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param editPrefsDescriptor editPrefsDescriptor to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewEditPrefsDescriptor(EditPrefsDescriptor editPrefsDescriptor) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), editPrefsDescriptor, getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param editDayDescriptor editDayDescriptor to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewEditDayDescriptor(EditDayFieldCommand.EditDayDescriptor editDayDescriptor) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), editDayDescriptor, getEditEventDescriptor(),
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());

    }

    /**
     * Immutable chained constructor.
     *
     * @param editEventDescriptor editEventDescriptor to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewEditEventDescriptor(EditEventFieldCommand.EditEventDescriptor editEventDescriptor) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), editEventDescriptor,
                getEditExpenditureDescriptor(), getEditDiaryEntryDescriptor());
    }

    /**
     * Immutable chained constructor.
     *
     * @param editExpenditureDescriptor editExpenditureDescriptor to use.
     * @return The new PageStatus instance.
     */
    public PageStatus withNewEditExpenditureDescriptor(EditExpenditureFieldCommand.EditExpenditureDescriptor
                                                               editExpenditureDescriptor) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                editExpenditureDescriptor, getEditDiaryEntryDescriptor());

    }

    public PageStatus withResetEditTripDescriptor() {
        return withNewEditTripDescriptor(null);
    }

    public PageStatus withResetEditPrefsDescriptor() {
        return withNewEditPrefsDescriptor(null);
    }

    public PageStatus withResetEditDayDescriptor() {
        return withNewEditDayDescriptor(null);
    }

    public PageStatus withResetEditEventDescriptor() {
        return withNewEditEventDescriptor(null);
    }

    public PageStatus withResetEditExpenditureDescriptor() {
        return withNewEditExpenditureDescriptor(null);
    }
    /**
     * Constructs a new {@link PageStatus} from the provided {@code editDiaryEntryDescriptor}.
     *
     * @param editDiaryEntryDescriptor {@link EditDiaryEntryDescriptor} to use.
     * @return The new {@link PageStatus} with the provided {@code editDiaryEntryDescriptor}.
     */
    public PageStatus withNewEditDiaryEntryDescriptor(EditDiaryEntryDescriptor editDiaryEntryDescriptor) {
        return new PageStatus(getPageType(), getTrip(), getDay(), getEvent(), getDiaryEntry(), getExpenditure(),
                getEditTripDescriptor(), getEditPrefsDescriptor(), getEditDayDescriptor(), getEditEventDescriptor(),
                getEditExpenditureDescriptor(), editDiaryEntryDescriptor);
    }

    public PageType getPageType() {
        return pageType;
    }

    public Trip getTrip() {
        return trip;
    }

    public Day getDay() {
        return day;
    }

    public Event getEvent() {
        return event;
    }

    public Expenditure getExpenditure() {
        return expenditure;
    }

    public DiaryEntry getDiaryEntry() {
        return diaryEntry;
    }

    public EditTripDescriptor getEditTripDescriptor() {
        return editTripDescriptor;
    }

    public EditDayFieldCommand.EditDayDescriptor getEditDayDescriptor() {
        return editDayDescriptor;
    }

    public EditEventFieldCommand.EditEventDescriptor getEditEventDescriptor() {
        return editEventDescriptor;
    }

    public EditExpenditureFieldCommand.EditExpenditureDescriptor getEditExpenditureDescriptor() {
        return editExpenditureDescriptor;
    }

    public EditPrefsDescriptor getEditPrefsDescriptor() {
        return editPrefsDescriptor;
    }

    public EditDiaryEntryDescriptor getEditDiaryEntryDescriptor() {
        return editDiaryEntryDescriptor;
    }
}