package cbstudios.coffeebreak.model.tododatamodule.todolist;
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.ListTask.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 

import java.util.List;

public class ListTask extends AdvancedTask implements IListTask {
    private List<ITask> subTasks;

    public boolean isChecked(){
        task.isChecked();
    }

    public void toggleChecked(){

    }

    public void setChecked(boolean value){
        task.setChecked(value);
    }

    public List<ITask> getSubTasks() {
        return subTasks;
    }

    public void addSubTask() {

    }
}
