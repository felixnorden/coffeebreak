package cbstudios.coffeebreak.model.tododatamodule.todolist;
/**
 * @author Felix
 * @version 1.0
 *
 * <p>Responsibility: Creation of all possible tasks that the application supports. </br>
 * Classes used: Task, AdvancedTask, ListTask </br>
 * Interfaces used: ITask, IAdvancedTask, IListTask </br>
 * Used by: ToDoDataModule and controllers outside Model through the ITaskFactory interface
 * when a form of task will be created. </br>
 * When application is initialized at every start to load in saved tasks from previous run
 * </p>
 */
class TaskFactory implements ITaskFactory {
    private static final ITaskFactory taskFactory = new TaskFactory();

    /**
     * Fetches the singleton instance of the TaskFactory.
     * @return      the TaskFactory instance of type ITaskFactory
     */
    public static ITaskFactory getInstance(){
        return taskFactory;
    }

    /**
     * {@inheritDoc}
     */
    public ITask createTask() {
        return new Task();
    }

    /**
     * {@inheritDoc}
     */
    public ITask createTask(String name) {
        return new Task(name);
    }

    /**
     * {@inheritDoc}
     */
    public IAdvancedTask createAdvancedTask() {
        return new AdvancedTask();
    }

    /**
     * {@inheritDoc}
     */
    public IAdvancedTask createAdvancedTask(String name) {
        return new AdvancedTask(name);
    }

    /**
     * {@inheritDoc}
     */
    public IListTask createListTask() {
        return new ListTask();
    }

    /**
     * {@inheritDoc}
     */
    public IListTask createListTask(String name) {
        return new ListTask(name);
    }

    private TaskFactory(){}
}
