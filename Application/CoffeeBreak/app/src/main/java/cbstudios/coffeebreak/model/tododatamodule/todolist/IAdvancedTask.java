package cbstudios.coffeebreak.model.tododatamodule.todolist;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import java.util.Calendar;
import java.util.List;

//
//  @ Project : Coffee Break
//  @ File Name : IAdvancedTask.java
//  @ Date : 03/04/2017
//  @ Author : Felix
//
//
public interface IAdvancedTask {

    /**
     * @return The name if the task
     */
    public String getName();

    /**
     * @param name The name for the task
     */
    public void setName(String name);

    /**
     * @return The date for when the task is due
     */
    public Calendar getDate();

    /**
     * @param date The date to set for the task
     */
    public void setDate(Calendar date);

    /**
     * @return The labels which the task is assigned
     */
    public List<ILabelCategory> getLabels();

    /**
     * @param label The label that will be added to the task
     */
    public void addLabel(ILabelCategory label);

    /**
     * @param label The label which will be removed from the task
     */
    public void removeLabel(ILabelCategory label);

    /**
     * @return True if the task has any note
     */
    public boolean hasNote();

    /**
     * @return The note that the task contained
     */
    public String getNote();

    /**
     * @param note the note that will be set for the task
     */
    public void setNote(String note);

    /**
     * @return True if the task has a priority defined
     */
    public boolean hasPriority();

    /**
     * @return The priority of the task
     */
    public Priority getPriority();

    /**
     * @param priority The priority which will be set on the task
     */
    public void setPriority(Priority priority);

    /**
     * @return True if the task has been checked off
     */
    public boolean isChecked();

    /**
     * Inverts the boolean value checked.
     * If checked is true, then false
     * if checked is false, then true
     */
    public void toggleChecked();

    /**
     * @param value The value to set task's checked value
     */
    public void setChecked(boolean value);

    /**
     *  @return Creation date and time for the task
     */
    public Calendar getCreationCalendar();
}
