package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Zack Lundqvist
 * @version 1.0
 * Responsibility: Contains information about the EditTask-event.
 */
public class EditTaskEvent {
    private final IAdvancedTask task;

    public EditTaskEvent(IAdvancedTask task) {
        this.task = task;
    }

    public IAdvancedTask getTask() {
        return this.task;
    }
}
