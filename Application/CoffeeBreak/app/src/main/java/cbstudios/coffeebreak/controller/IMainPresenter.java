package cbstudios.coffeebreak.controller;

import java.util.List;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

/**
 * Created by Lenovo on 2017-05-08.
 */

public interface IMainPresenter extends IPresenter {
    void createAdvancedTask();
    void createListTask();
    void createTask(IListTask listTask);
    //void removeTask(IAdvancedTask task);
    Model getModel();
    List<IAdvancedTask> getTasks();
}
