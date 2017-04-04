package cbstudios.coffeebreak.model.tododatamodule.todolist;//

//
//  @ Project : Untitled
//  @ File Name : ITaskFactory.java
//  @ Date : 03/04/2017
//  @ Author : 
//
public interface ITaskFactory {

    /**
     * Creates an empty task
     * @return
     */
    public ITask createTask();

    public ITask createTask(String name);

    public IAdvancedTask createAdvancedTask();

    public IAdvancedTask createAdvancedTask(String name);

    public IListTask createListTask();

    public IListTask createListTask(String name);
}
