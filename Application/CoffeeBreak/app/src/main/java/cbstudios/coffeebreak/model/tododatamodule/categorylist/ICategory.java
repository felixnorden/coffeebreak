package cbstudios.coffeebreak.model.tododatamodule.categorylist;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * Created by elias on 23/04/2017.
 */

public interface ICategory {
    String getName();
    List<IAdvancedTask> getValidTasks(List<IAdvancedTask> tasks);
}
