package cbstudios.coffeebreak.model.tododatamodule.todolist;
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.tododatamodule.todolist.ToDoList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//


import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private ITaskFactory taskFactory;
    private List<IAdvancedTask> tasks;

    /**
     * Class constructor
     */
    public ToDoList(){
        initComponents();
    }

    /**
     * Adds a task that implements the interface IAdvancedTask to the
     * ToDoList's list of existing tasks.
     * @param task  the task that will be added
     */
    public void add(IAdvancedTask task){
        tasks.add(task);
    }

    /**
     * Removes the specified task from ToDoList's list of tasks,
     * with regard to reference.
     * @param task  the task that will be removed
     */
    public void remove(IAdvancedTask task){
        int index = 0;
        boolean found = false;
        for(int i = 0; i < tasks.size(); i++){
            if(task == tasks.get(i)){
                index = i;
                found = true;
                break;
            }
        }
        if(found)
            tasks.remove(index);
    }

    /**
     * Returns a the list of tasks in ToDoList
     * @return      the list of tasks in ToDoList
     */
    public List<IAdvancedTask> getTasks(){
        return new ArrayList<>(tasks);
    }

    /**
     * Returns the boolean value of  the specified task being
     * of the list is of the type IListTask
     * @param advancedTask  the task that is to be checked
     * @return      true if the task is of type IListTask
     */
    public boolean isListTask(IAdvancedTask advancedTask){
        return advancedTask instanceof IListTask;
    }

    private void initComponents() {
        taskFactory = TaskFactory.getInstance();
        tasks = new ArrayList<>();
        loadSavedData();
    }

    private void loadSavedData(){

    }

    /**
     * Creates a new Advanced Task and adds it to the List
     */
    public void createTask(){
        add(taskFactory.createAdvancedTask());
    }
}
