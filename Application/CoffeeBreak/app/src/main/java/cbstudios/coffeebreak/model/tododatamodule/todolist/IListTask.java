package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.List;

//  @ Project : Untitled
//  @ File Name : IListTask.java
//  @ Date : 03/04/2017
//  @ Author : 
//
public interface IListTask {
    public List<ITask> getSubTasks();
    public void addSubTask(ITask subtask);
}
