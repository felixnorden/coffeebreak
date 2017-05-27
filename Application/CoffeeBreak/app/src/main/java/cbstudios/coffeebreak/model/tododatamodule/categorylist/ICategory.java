package cbstudios.coffeebreak.model.tododatamodule.categorylist;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Elias
 * @version 1.0
 *          <p>Responsibility: A abstraction interface for a category</br >
 *          Uses: IAdvancedTask. </br>
 *          Used by: AchievementActivity, CategoryList, TaskAdapter, MainActivity, ITimeCategory, ITaskAdapter, IMainView, ILabelCategory.
 *          </p>
 */

public interface ICategory {
    /**
     * @return the name of the category
     */
    String getName();

    /**
     * Set a new name to a labelCategory
     *
     * @param name is the new name of a labelCategory
     */
    void setName(String name);

    /**
     * Filters a list of tasks so it only contains the tasks in this specific category
     *
     * @param tasks The list of tasks to be filtered
     * @return The filtered list.
     */
    List<IAdvancedTask> getValidTasks(List<IAdvancedTask> tasks);

    /**
     * Returns the amount in the given list that belongs to this category.
     *
     * @param tasks The list to be checked
     * @return How many tasks in this list that belongs to the category.
     */
    int getTaskCount(List<IAdvancedTask> tasks);
}
