package cbstudios.coffeebreak.model.tododatamodule.todolist;//

/**
 * @author Felix
 * @version 1.0
 * <p>Responsibility: Abstraction of the TaskFactory class. <\br>
 * Classes used: Task, AdvancedTask, ListTask <\br>
 * Interfaces used: ITask, IAdvancedTask, IListTask <\br>
 * Used by: ToDoDataModule and controllers outside Model when a form of task will be created.
 * When application is initialized at every start to load in saved tasks from previous run
 * </p>
 */
public interface ITaskFactory {

    /**
     * Creates an empty task
     * @return      a task of type ITask
     */
    ITask createTask();

    /**
     * Creates a task with the given name
     * @param name  the name for the task
     * @return      a task of type ITask
     */
    ITask createTask(String name);

    /**
     * Creates an empty advanced task
     * @return      a task of type IAdvancedTask
     */
    IAdvancedTask createAdvancedTask();

    /**
     * Creates an advanced task with the given name
     * @param name  the name for the advanced task
     * @return      a task of type IAdvancedTask
     */
    IAdvancedTask createAdvancedTask(String name);

    /**
     * Creates an empty list task
     * @return      a task of type IListTask
     */
    IListTask createListTask();

    /**
     * Creates a list task with the given name
     * @param name  the name for the list task
     * @return      a task of type IListTask
     */
    IListTask createListTask(String name);
}
