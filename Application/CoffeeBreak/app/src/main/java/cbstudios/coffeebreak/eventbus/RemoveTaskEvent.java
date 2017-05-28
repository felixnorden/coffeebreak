package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when a task needs to be removed </br >
 *          Used by: MainPresenter, TaskAdapter
 *          </p>
 */

public class RemoveTaskEvent {
    public final IAdvancedTask task;
    public final boolean checked;

    /**
     *
     * @param task is the task that will be deleted
     * @param checked is true if the task will be deleted, false if not
     */
    public RemoveTaskEvent(IAdvancedTask task, boolean checked){
        this.task = task;
        this.checked = checked;
    }
}
