package cbstudios.coffeebreak.model.tododatamodule.todolist;//

//
//  @ Project : Untitled
//  @ File Name : ITaskFactory.java
//  @ Date : 03/04/2017
//  @ Author : 
//
public interface ITaskFactory {
    public ITask createTask();

    public IAdvancedTask createAdvancedTask();

    public IListTask createListTask();
}
