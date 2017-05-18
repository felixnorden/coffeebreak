package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

public class EditTaskActivityEvent implements IEvent {
    private final IAdvancedTask task;

    public EditTaskActivityEvent(IAdvancedTask task) {
        this.task = task;
    }

    public IAdvancedTask getTask() {
        return this.task;
    }

}
