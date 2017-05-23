package cbstudios.coffeebreak.view.adapter;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * Created by Lenovo on 2017-05-08.
 */

public interface ITaskAdapter {
    void updateTasks();
    void filterTasks();
    void updateTmpTasks(List<IAdvancedTask> tasks);
    void refreshItems();
}
