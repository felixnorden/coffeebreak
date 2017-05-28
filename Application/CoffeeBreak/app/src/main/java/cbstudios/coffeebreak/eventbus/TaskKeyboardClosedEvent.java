package cbstudios.coffeebreak.eventbus;

import android.view.View;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Felix
 * @version 1.0
 */

public class TaskKeyboardClosedEvent implements IEvent {
    public final View view;
    public final int position;
    public final boolean removeTask;
    public final IAdvancedTask task;

    /**
     *
     * @param view is the current view
     * @param position is the current position
     * @param removeTask is true if the task will be removed, false if not
     * @param task is the current task
     */
    public TaskKeyboardClosedEvent(View view, int position, boolean removeTask, IAdvancedTask task) {
        this.view = view;
        this.position = position;
        this.removeTask = removeTask;
        this.task = task;
    }
}
