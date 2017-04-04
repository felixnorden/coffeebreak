package cbstudios.coffeebreak.model.tododatamodule.todolist;
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.ListTask.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 

import java.util.ArrayList;
import java.util.List;

public class ListTask extends AdvancedTask implements IListTask {
    private final List<ITask> subtasks;

    public ListTask(){
        subtasks = new ArrayList<>();
    }
    public List<ITask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(ITask subtask) {
        subtasks.add(subtask);
    }

    public void removeSubtask(ITask subtask) {
        if(subtasks.contains(subtask))
            subtasks.remove(subtask);
    }

}
