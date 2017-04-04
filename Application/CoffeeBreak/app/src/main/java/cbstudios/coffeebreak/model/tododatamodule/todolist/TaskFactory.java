package cbstudios.coffeebreak.model.tododatamodule.todolist;
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.TaskFactory.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//
public class TaskFactory implements ITaskFactory {
    public ITask createTask() {
        return new Task();
    }

    public IAdvancedTask createAdvancedTask() {
        return new AdvancedTask();
    }

    public IListTask createListTask() {
        return new ListTask();
    }
}
