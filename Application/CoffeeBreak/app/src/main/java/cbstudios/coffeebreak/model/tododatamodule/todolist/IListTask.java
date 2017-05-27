package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.List;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Abstraction interface for the implementing class List Task</br>
 *          Used by: All controllers and subsequent classes handling tasks in a list.
 *          </p>
 */

public interface IListTask extends IAdvancedTask {
    /**
     * @return A list of the current subtasks added to the ListTask.
     */
    List<ITask> getSubtasks();

    /**
     * Add a subtask to the listtask.
     *
     * @param subtask The subtask to be added.
     */
    void add(ITask subtask);

    /**
     * Remove a subtask from the listtask.
     *
     * @param subtask The subtask to be removed.
     */
    void remove(ITask subtask);

    //TODO add implementation for getting a specific task in the list
}
