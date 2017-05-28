package cbstudios.coffeebreak.view.adapter;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Felix, Elias
 * @version 2.0
 *          <p>Responsibility: Abstracting interface for {@link android.widget.Adapter} instances that
 *          handle lists of {@link IAdvancedTask} and {@link cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask}</br >
 *          Uses: {@link IAdvancedTask}</br>
 *          Used by: {@link cbstudios.coffeebreak.controller.MainPresenter}
 *          </p>
 */

public interface ITaskAdapter {

    /**
     * Filter the list of tasks to the specific {@link ICategory}
     * and updates the view
     * @param category the specific category
     */
    void filterTasks(ICategory category);

    /**
     * Update the list of temporary tasks in the adapter for
     * efficient update of {@link android.support.v7.widget.RecyclerView}
     * @param tasks the current list of tasks in {@link cbstudios.coffeebreak.model.Model}
     */
    void updateTmpTasks(List<IAdvancedTask> tasks);

    /**
     * Update the list of tasks for removing checked off tasks
     * @param currentCategory the category for displaying the correct items
     */
    void refreshItems(ICategory currentCategory);

    /**
     * Update the list of tasks by analyzing and removing the unvalid views of the adapater
     * @param tasks
     */
    void swapTasks(List<IAdvancedTask> tasks);
}
