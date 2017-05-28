package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * @author Zack, Felix
 * @version 1.0
 *          <p>Responsibility: Represents the data in  the ToDo list</br >
 *          Uses: IAdvancedTask, ITaskFactory, TaskFactory, IListTask</br>
 *          Used by:
 *          </p>
 */
public class ToDoList {
    private ITaskFactory taskFactory;
    private List<IAdvancedTask> tasks;

    /**
     * Class constructor
     */
    public ToDoList() {
        initComponents();
    }

    /**
     * Adds a task that implements the interface IAdvancedTask to the
     * ToDoList's list of existing tasks.
     *
     * @param task the task that will be added
     */
    public void add(IAdvancedTask task) {
        tasks.add(task);
    }

    /**
     * Removes the specified task from ToDoList's list of tasks,
     * with regard to reference.
     *
     * @param task the task that will be removed
     */
    public void remove(IAdvancedTask task) {
        int index = 0;
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (task == tasks.get(i)) {
                index = i;
                found = true;
                break;
            }
        }
        if (found) {
            tasks.remove(index);
        }
    }

    /**
     * Returns a the list of tasks in ToDoList
     *
     * @return the list of tasks in ToDoList
     */
    public List<IAdvancedTask> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns the boolean value of  the specified task being
     * of the list is of the type IListTask
     *
     * @param advancedTask the task that is to be checked
     * @return true if the task is of type IListTask
     */
    public boolean isListTask(IAdvancedTask advancedTask) {
        return advancedTask instanceof IListTask;
    }

    /**
     * Initiates the instance.
     */
    private void initComponents() {
        taskFactory = TaskFactory.getInstance();
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new Advanced Task and adds it to the List
     */
    public void createAdvancedTask() {
        add(taskFactory.createAdvancedTask());
    }

    /**
     * Searches for a task with the given hashcode in the database.
     *
     * @param hashCode The hashcode of the task.
     * @return The task with matching hashcode if it exists, null otherwise.
     */
    public IAdvancedTask findTask(int hashCode) {
        IAdvancedTask match = null;

        for (IAdvancedTask task : tasks) {
            if (task.hashCode() == hashCode) {
                match = task;
                break;
            }
        }
        return match;
    }

    /**
     * Removes a LabelCategory from all tasks. The category only gets removed if it equals to
     * the category given.
     *
     * @param category The category to be removed.
     */
    public void removeLabelCategoryFromTasks(ILabelCategory category) {
        for (IAdvancedTask task : tasks) {
            if (task.getLabels().contains(category)) {
                task.removeLabel(category);
            }
        }

    }

    /**
     * Creates a Task and adds it to the references ListTask
     *
     * @param listTask ListTask to hold the task
     */
    public void createTask(IListTask listTask) {
        listTask.add(taskFactory.createTask());
    }

    /**
     * Creates a ListTask and adds it to the list.
     */
    public void createListTask() {
        add(taskFactory.createListTask());
    }

    /**
     * Overrides the current list of tasks with a new one.
     *
     * @param tasks The new list of tasks.
     */
    public void setTasks(List<IAdvancedTask> tasks) {
        this.tasks = tasks;
    }
}
