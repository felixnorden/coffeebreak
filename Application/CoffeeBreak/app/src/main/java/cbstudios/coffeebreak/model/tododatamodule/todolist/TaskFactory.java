package cbstudios.coffeebreak.model.tododatamodule.todolist;
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.TaskFactory.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//
public class TaskFactory implements ITaskFactory {
    private static final ITaskFactory taskFactory = new TaskFactory();

    public static ITaskFactory getInstance(){
        return taskFactory;
    }

    public ITask createTask() {
        return new Task();
    }

    public ITask createTask(String name) {
        return new Task(name);
    }

    public IAdvancedTask createAdvancedTask() {
        return new AdvancedTask();
    }

    public IAdvancedTask createAdvancedTask(String name) {
        return new AdvancedTask(name);
    }

    public IListTask createListTask() {
        return new ListTask();
    }

    public IListTask createListTask(String name) {
        return new ListTask(name);
    }

    private TaskFactory(){}
}
