package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.List;

//  @ Project : Untitled
//  @ File Name : IListTask.java
//  @ Date : 03/04/2017
//  @ Author : 
//
public interface IListTask {
    public List<ITask> getSubtasks();
    public void add(ITask subtask);
    public void remove(ITask subtask);

    //TODO add implementation for getting a specific task in the list
}
