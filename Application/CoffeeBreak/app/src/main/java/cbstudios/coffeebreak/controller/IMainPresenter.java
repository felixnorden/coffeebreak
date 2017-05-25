package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ToDoList;

/**
 * @author Felix
 * @version 1.2
 *          <p>Responsibility: Interface for the {@link MainPresenter}</br >
 *          Used by: {@link IPresenterFactory}
 *          </p>
 */

public interface IMainPresenter extends IPresenter {

    /**
     * Creates an instance of {@link IAdvancedTask} and adds it to the {@link ToDoList}
     * in the model
     */
    void createAdvancedTask();

    /**
     * Creates an instance of{@link IListTask} and adds it to the {@link cbstudios.coffeebreak.model.tododatamodule.todolist.ToDoList}
     * in the model
     */
    void createListTask();

    /**
     * Creates an instance of {@link ITask} and adds it to the list in the chosen ListTask
     * @param listTask  the chosen ListTask
     */
    void createTask(IListTask listTask);

}
