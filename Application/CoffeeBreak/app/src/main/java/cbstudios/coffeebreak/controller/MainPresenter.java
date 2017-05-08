package cbstudios.coffeebreak.controller;

import java.util.List;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;

/**
 * @author Felix, Elias
 * @version 0.1
 *
 */
class MainPresenter implements IMainPresenter{
    private Model model;
    private IMainView mainView;
    private ITaskAdapter taskAdapter;


    public MainPresenter(IMainView mainView, ITaskAdapter taskAdapter){
        this.mainView = mainView;
        this.taskAdapter = taskAdapter;
    }
    @Override
    public void onCreate() {
        model = new Model();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void createTask() {
        model.getToDoDataModule().createTask();
    }

    @Override
    public void removeTask(IAdvancedTask task) {
        model.getToDoDataModule().removeTask(task);
    }

    @Override
    public List<IAdvancedTask> getTasks() {
        return model.getToDoDataModule().getTasks();
    }

    @Override
    public List<ILabelCategory> getLabelCategories() {
        return model.getToDoDataModule().getLabelCategories();
    }

    @Override
    public List<ITimeCategory> getTimeCategories() {
        return model.getToDoDataModule().getTimeCategories();
    }
}
