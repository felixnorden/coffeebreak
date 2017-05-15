package cbstudios.coffeebreak.model.tododatamodule.categorylist;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Felix, Elias
 * @version 1.0
 * Responsibility: Represent all available tasks.
 * Used by: TaskAdapter and MainActivity.
 */

final class AllCategory implements ICategory {
    private final String name = "All";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IAdvancedTask> getValidTasks(List<IAdvancedTask> tasks) {
        return tasks;
    }

    @Override
    public int getTaskCount(List<IAdvancedTask> tasks) {
        return tasks.size();
    }
}
