package cbstudios.coffeebreak.controller;

import java.util.List;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

/**
 * Created by Lenovo on 2017-05-08.
 */

public interface IMainPresenter extends IPresenter {
    void setTaskAdapter(TaskAdapter adapter);
    void createTask();
    void removeTask(IAdvancedTask task);
    //Model getModel();
    void registerComponentsToEventBus();


    List<IAdvancedTask> getTasks();
    List<ILabelCategory> getLabelCategories();
    List<ITimeCategory> getTimeCategories();
}
