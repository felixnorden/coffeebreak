package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.Calendar;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Abstraction for the Task class.</br>
 *          Used by: AdvancedTask and ListTask classes for holding the name, checked value and time of creation
 *          attributes. Also used in ListTask's list of subtasks.
 *          </p>
 */
public interface ITask {

    /**
     * @return the name of the task
     */
    public String getName();

    /**
     * @param name the name to set for the task
     */
    public void setName(String name);

    /**
     * @return True if task is checked
     */
    public boolean isChecked();

    /**
     * Inverts the boolean value for the checked attribute.
     * False if previously True, and vice versa.
     */
    public void toggleChecked();

    /**
     * @param checked boolean value of checked attribute
     */
    public void setChecked(boolean checked);

    /**
     * @return time of creation for this task
     */
    public Calendar getCreationCalendar();

    /**
     * Sets the creation date for a task. Needed for initialisation of tasks from save.
     * @param calendar The date the task was created.
     */
    public void setCreationCalendar(Calendar calendar);
}
