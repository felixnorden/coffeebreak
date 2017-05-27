package cbstudios.coffeebreak.model.tododatamodule;


import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ToDoList;

/**
 * @author Zack
 * @version 1.0
 *          Responsibility: The facade for the ToDo-list model.
 *          Uses: CategoryList, ICategoryList, ILabelCategory, ITimeCategory, Statistics, IAdvancedTask, IListTask, ToDoList.
 *          Used by: Model
 */
public class ToDoDataModule {
    private ICategoryList categoryList;
    private ToDoList todoList;
    private Statistics stats;

    public ToDoDataModule() {
        categoryList = new CategoryList();
        todoList = new ToDoList();
        stats = new Statistics();
    }

    /**
     * @return List of all {@link ILabelCategory} in the model.
     */
    public List<ILabelCategory> getLabelCategories() {
        return categoryList.getLabelCategories();
    }

    /**
     * Creates a new category with the given name
     *
     * @param name The name of the category to be created
     */
    public void addLabelCategory(String name) {
        categoryList.addLabelCategory(name);
    }

    /**
     * Creates a new nameless category
     */
    public void addLabelCategory() {
        categoryList.addLabelCategory();
    }

    /**
     * Adds a category to the model.
     *
     * @param category The category to be added.
     */
    public void addILabelCategory(ILabelCategory category) {
        categoryList.addLabelCategory(category);
    }

    /**
     * Returns a ILabelCategory with the given name. If it already exists in the model, that
     * is returned, otherwise a ILabelCategory with that name is created and returned.
     *
     * @param name The name of the ILabelCategory
     * @return The requested ILabelCategory
     */
    public ILabelCategory getLabelCategory(String name) {
        return categoryList.getLabelCategory(name);
    }

    /**
     * Should always return the 4 default ITimeCategories (Today, Tomorrow, Week, Month)
     *
     * @return Returns a list of the ITimeCategories in the model
     */
    public List<ITimeCategory> getTimeCategories() {
        return categoryList.getTimeCategories();
    }

    /**
     * Removes all the checked Tasks from the TodoList
     */
    public void removeChecked() {
        List<IAdvancedTask> toBeRemoved = new ArrayList<>();
        for (IAdvancedTask advancedTask : getTasks()) {
            if (advancedTask.isChecked())
                toBeRemoved.add(advancedTask);
        }
        getTasks().removeAll(toBeRemoved);
    }

    public void removeCategoryFromTasks(int position) {

      ILabelCategory category = categoryList.getLabelCategories().get(position);
    todoList.removeCategoryFromTasks(category);
}

    /**
     * Removes a specific category from the model. If the category doesn't exist, nothing happens.
     *
     * @param name Tha name of the category to be removed
     */
    public void removeLabelCategory(String name) {
        categoryList.removeLabelCategory(name);
    }

    /**
     * Removes a specific category from the model. If the category doesn't exist, nothing happens.
     *
     * @param labelCategory The reference to the category to be removed.
     */
    public void removeLabelCategory(ILabelCategory labelCategory) {
        categoryList.removeLabelCategory(labelCategory);
    }

    /**
     * Creates a new AdvancedTask
     */
    public void createAdvancedTask() {
        todoList.createAdvancedTask();
    }

    /**
     * Creates a new ListTask
     */
    public void createListTask() {
        todoList.createListTask();
    }

    /**
     * Creates a Task and adds it to the ListTask's list
     *
     * @param listTask The ListTask holding subTasks
     */
    public void createTask(IListTask listTask) {
        todoList.createTask(listTask);
    }

    /**
     * Adds a already created task to the ToDoList. This task should be created by the ITaskFactory.
     *
     * @param task The specific task to be added.
     */
    public void addTask(IAdvancedTask task) {
        todoList.add(task);
    }

    /**
     * @return Returns the tasks in the ToDoList.
     */
    public List<IAdvancedTask> getTasks() {
        return todoList.getTasks();
    }

    /**
     * Returns true or false whether the given task is a IListTask
     *
     * @param task The task to check
     * @return True if instance of IListTask, false otherwise.
     */
    public boolean isListTask(IAdvancedTask task) {
        return todoList.isListTask(task);
    }

    /**
     * Removes a specific task from the model. If the task doesn't exist, nothing happens.
     *
     * @param task The task to be removed.
     */
    public void removeTask(IAdvancedTask task) {
        todoList.remove(task);
    }

    /**
     * @param index Index of the task in ToDoList
     * @return The task at place index in ToDoList
     */
    public IAdvancedTask getTask(int index) {
        return todoList.getTasks().get(index);
    }

    /**
     * Searches for a task with the given hashCode in the database.
     *
     * @param hashCode The hashCode of the task.
     * @return The task with matching hashCode if it exists, null otherwise.
     */
    public IAdvancedTask findTask(int hashCode) {
        return todoList.findTask(hashCode);
    }

    /**
     * @return The statistics module of the model.
     */
    public Statistics getStats() {
        return stats;
    }

    /**
     * Overrides the current statistic with a new module.
     *
     * @param statistics The new statistics module.
     */
    public void setStatistic(Statistics statistics) {
        this.stats = statistics;
    }

    /**
     * Overrides the current list of tasks with a new list.
     * Should be used when a sorting of the current list has occurred.
     *
     * @param tasks The new list of tasks.
     */
    public void setTasks(List<IAdvancedTask> tasks) {
        todoList.setTasks(tasks);
    }
}
