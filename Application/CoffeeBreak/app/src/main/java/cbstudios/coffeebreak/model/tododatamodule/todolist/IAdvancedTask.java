package cbstudios.coffeebreak.model.tododatamodule.todolist;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import java.util.Calendar;
import java.util.List;

/**
 * @author Felix
 * @version 1.0
 * <p>Responsibility: Abstraction interface for the implementing class Advanced Task
 * and the extending class List Task</br>
 * Used by: All controllers and subsequent classes handling tasks in a list.
 * </p>
 */
public interface IAdvancedTask {

    /**
     * @return          the name if the task
     */
    public String getName();

    /**
     * @param name      the name for the task
     */
    public void setName(String name);

    /**
     * @return          the date for when the task is due
     */
    public Calendar getDate();

    /**
     * @param date      the date to set for the task
     */
    public void setDate(Calendar date);

    /**
     * @return          the labels which the task is assigned
     */
    public List<ILabelCategory> getLabels();

    /**
     * @param label     the label that will be added to the task
     */
    public void addLabel(ILabelCategory label);

    /**
     * @param label     the label which will be removed from the task
     */
    public void removeLabel(ILabelCategory label);

    /**
     * @return          True if the task has any note
     */
    public boolean hasNote();

    /**
     * @return          the note that the task contained
     */
    public String getNote();

    /**
     * @param note      the note that will be set for the task
     */
    public void setNote(String note);

    /**
     * @return          the priority of the task
     */
    public Priority getPriority();

    /**
     * @param priority  the priority which will be set on the task
     */
    public void setPriority(Priority priority);

    /**
     * @return          True if the task has been checked off
     */
    public boolean isChecked();

    /**
     * Inverts the boolean value checked.
     * If checked is true, then false
     * if checked is false, then true
     */
    public void toggleChecked();

    /**
     * @param value     the value to set task's checked value
     */
    public void setChecked(boolean value);

    /**
     *  @return         creation date and time for the task
     */
    public Calendar getCreationCalendar();

    /**
     *
     * @param calendar
     */
    public void setCreationCalendar(Calendar calendar);
}
