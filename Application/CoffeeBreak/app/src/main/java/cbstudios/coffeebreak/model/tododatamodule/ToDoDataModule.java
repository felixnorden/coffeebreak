package cbstudios.coffeebreak.model.tododatamodule;
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.ToDoDataModule.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 


import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ToDoList;

public class ToDoDataModule {
    private ICategoryList categoryList;
    private ToDoList todoList;

    /**
     *
     */
    public ToDoDataModule() {
        categoryList = new CategoryList();
        todoList = new ToDoList();
    }

    /**
     * Initializes the two components of the datamodule.
     */

    public void initialize() {
        initCategories();
        initToDoList();
    }

    /**
     * Creates a new category with the given name
     *
     * @param name The name of the category to be created
     */
    public void addLabelCategory(String name, int color) {
        categoryList.addLabelCategory(name, color);
    }

    /**
     * Should always return the 4 default ITimeCategories (Today, Tomorrow, Week, Month)
     *
     * @return Returns a list of the ITimeCategories in the model
     */
    public List<ITimeCategory> getTimeCategories() {
        return categoryList.getTimeCategories();
    }

    /**
     * Initializes the categories with saved data
     */
    private void initCategories() {
        // TODO: 2017-04-04
        //SKALL BYTAS UT MOT initCategories NÄR DEN IMPLEMENTERATS!!!
        categoryList.initTimeCategories();
    }

    /**
     * Initializes the categories with saved data
     */
    private void initToDoList() {
        //// TODO: 2017-04-04
    }

    /**
     * Removes a specific category from the model. If the category doesn't exist, nothing happens.
     *
     * @param name Tha name of the category to be removed
     */
    public void removeLabelCategory(String name) {
        categoryList.removeLabelCategory(name);
    }

    /**
     * Removes a specific category from the model. If the category doesn't exist, nothing happens.
     *
     * @param labelCategory The reference to the category to be removed.
     */
    public void removeLabelCategory(ILabelCategory labelCategory) {
        categoryList.removeLabelCategory(labelCategory);
    }

    /**
     * Adds a already created task to the ToDoList. This task should be created by the ITaskFactory.
     *
     * @param task The specific task to be added.
     */
    public void addTask(IAdvancedTask task) {
        todoList.add(task);
    }

    /**
     * @return Returns the tasks in the ToDoList.
     */
    public List<IAdvancedTask> getTasks() {
        return todoList.getTasks();
    }

    /**
     * Returns true or false whether the given task is a IListTask
     *
     * @param task The task to check
     * @return True if instance of IListTask, false otherwise.
     */
    public boolean isListTask(IAdvancedTask task) {
        return todoList.isListTask(task);
    }

    /**
     * Removes a specific task from the model. If the task doesn't exist, nothing happens.
     *
     * @param task The task to be removed.
     */
    public void removeTask(IAdvancedTask task) {
        todoList.remove(task);
    }

    /**
     * @param index Index of the task in ToDoList
     * @return The task at place index in ToDoList
     */
    public IAdvancedTask getTask(int index){
        return todoList.getTasks().get(index);
    }
}
