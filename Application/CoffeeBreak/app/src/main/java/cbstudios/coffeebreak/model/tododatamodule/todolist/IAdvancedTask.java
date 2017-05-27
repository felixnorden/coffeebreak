package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Abstraction interface for the implementing class Advanced Task
 *          and the extending class List Task</br>
 *          Used by: All controllers and subsequent classes handling tasks in a list.
 *          </p>
 */
public interface IAdvancedTask {

    /**
     * @return the name if the task
     */
    String getName();

    /**
     * @param name the name for the task
     */
    void setName(String name);

    /**
     * @return the date for when the task is due
     */
    Calendar getNotification();

    /**
     * @param date the date to set for the task
     */
    void getNotification(Calendar date);

    /**
     * @return the labels which the task is assigned
     */
    List<ILabelCategory> getLabels();

    /**
     * @param label the label that will be added to the task
     */
    void addLabel(ILabelCategory label);

    /**
     * @param label the label which will be removed from the task
     */
    void removeLabel(ILabelCategory label);

    /**
     * @return True if the task has any note
     */
    boolean hasNote();

    /**
     * @return the note that the task contained
     */
    String getNote();

    /**
     * @param note the note that will be set for the task
     */
    void setNote(String note);

    /**
     * @return the priority of the task
     */
    Priority getPriority();

    /**
     * @param priority the priority which will be set on the task
     */
    void setPriority(Priority priority);

    /**
     * @return True if the task has been checked off
     */
    boolean isChecked();

    /**
     * Inverts the boolean value checked.
     * If checked is true, then false
     * if checked is false, then true
     */
    void toggleChecked();

    /**
     * @param value the value to set task's checked value
     */
    void setChecked(boolean value);

    /**
     * @return Creation date and time for the task
     */
    Calendar getCreationCalendar();

    /**
     * @param calendar Creation date to set for the task
     */
    void setCreationCalendar(Calendar calendar);

    /**
     * @param name Name of category
     * @return True if true, false if false (big if true)
     */
    boolean hasILabelCategory(String name);
}
