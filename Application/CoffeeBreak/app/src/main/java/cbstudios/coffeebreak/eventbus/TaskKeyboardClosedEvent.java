package cbstudios.coffeebreak.eventbus;

import android.view.View;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;

/**
 * @author Felix
 * @version 1.0
 */

public class TaskKeyboardClosedEvent {
    public final View view;
    public final int position;
    public final boolean removeTask;
    public final IAdvancedTask task;

    public TaskKeyboardClosedEvent(View view, int position, boolean removeTask, IAdvancedTask task){
        this.view = view;
        this.position = position;
        this.removeTask = removeTask;
        this.task = task;
    }
}
