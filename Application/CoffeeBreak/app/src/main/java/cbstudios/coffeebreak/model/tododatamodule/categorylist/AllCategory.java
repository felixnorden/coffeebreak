package cbstudios.coffeebreak.model.tododatamodule.categorylist;

import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Felix, Elias
 * @version 1.0
 * Responsibility: Represent all available tasks.
 * Used by: TaskAdapter and MainActivity.
 */

 class AllCategory implements ITimeCategory {
    private String name = "All";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isInIntervall(Calendar date) {
        return true;
    }

    @Override
    public Calendar getTime() {
        return null;
    }

    @Override
    public void setTime(Calendar time) {

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
