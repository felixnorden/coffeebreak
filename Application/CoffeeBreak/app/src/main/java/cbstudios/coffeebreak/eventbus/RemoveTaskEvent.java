package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;

/**
 * Created by Felix on 2017-05-23.
 */

public class RemoveTaskEvent {
    public final IAdvancedTask task;
    public final boolean checked;

    public RemoveTaskEvent(IAdvancedTask task, boolean checked){
        this.task = task;
        this.checked = checked;
    }
}
