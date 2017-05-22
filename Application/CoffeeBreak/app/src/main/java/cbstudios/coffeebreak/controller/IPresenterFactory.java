package cbstudios.coffeebreak.controller;

import android.content.Context;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.ITaskEditView;

public interface IPresenterFactory {
    IMainPresenter createMainPresenter(IMainView mainView, Model model);

    ITaskEditPresenter createTaskDetailPresenter(IAdvancedTask task);

    DelegatingPresenter initializeDelegatingPresenter(Context context);

    DelegatingPresenter initializeDelegatingPresenter();
}
