package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.ITaskEditView;

public interface IPresenterFactory {
    IMainPresenter createMainPresenter(IMainView mainView);

    ITaskEditPresenter createTaskDetailPresenter(IAdvancedTask task);
}
