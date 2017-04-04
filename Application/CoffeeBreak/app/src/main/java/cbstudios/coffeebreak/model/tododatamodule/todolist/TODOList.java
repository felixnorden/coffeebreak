package cbstudios.coffeebreak.model.tododatamodule.todolist;
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.tododatamodule.todolist.TODOList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//


import java.util.ArrayList;
import java.util.List;

public class TODOList {
    private ITaskFactory taskFactory;
    private List<IAdvancedTask> tasks;

    //Constructor
    public TODOList(){
        initComponents();
    }

    public void add(IAdvancedTask task){
        tasks.add(task);
    }

    public void remove(IAdvancedTask task){
        if(tasks.contains(task)){
            tasks.remove(task);
        }
    }

    public List<IAdvancedTask> getTasks(){
        return tasks;
    }

    public boolean isListTask(IAdvancedTask advancedTask){
        return advancedTask instanceof IListTask;
    }
    private void initComponents() {
        taskFactory = TaskFactory.getInstance();
        tasks = new ArrayList<>();
        loadLastExecution();
    }

    private void loadLastExecution(){

    }
}
