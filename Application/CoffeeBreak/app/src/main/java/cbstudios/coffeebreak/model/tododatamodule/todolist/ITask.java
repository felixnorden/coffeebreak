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
    String getName();

    /**
     * @param name the name to set for the task
     */
    void setName(String name);

    /**
     * @return True if task is checked
     */
    boolean isChecked();

    /**
     * Inverts the boolean value for the checked attribute.
     * False if previously True, and vice versa.
     */
    void toggleChecked();

    /**
     * @param checked boolean value of checked attribute
     */
    void setChecked(boolean checked);

    /**
     * @return time of creation for this task
     */
    Calendar getCreationCalendar();

    /**
     * Sets the creation date for a task. Needed for initialisation of tasks from save.
     * @param calendar The date the task was created.
     */
    void setCreationCalendar(Calendar calendar);
}
