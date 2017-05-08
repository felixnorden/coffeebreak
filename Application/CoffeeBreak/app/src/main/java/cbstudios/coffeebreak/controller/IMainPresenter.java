package cbstudios.coffeebreak.controller;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * Created by Lenovo on 2017-05-08.
 */

public interface IMainPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
    void createTask();
    void removeTask(IAdvancedTask task);


    List<IAdvancedTask> getTasks();
    List<ILabelCategory> getLabelCategories();
    List<ITimeCategory> getTimeCategories();
}
