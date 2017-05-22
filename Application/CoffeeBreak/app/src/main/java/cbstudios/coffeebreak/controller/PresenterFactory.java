package cbstudios.coffeebreak.controller;

import android.content.Context;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.ITaskEditView;

/**
 * @author Felix, Elias
 * @version 0.1
 *
 */

public class PresenterFactory implements IPresenterFactory{
    private static IPresenterFactory INSTANCE = new PresenterFactory();

    public static IPresenterFactory getInstance(){
        return INSTANCE;
    }

    @Override
    public IMainPresenter createMainPresenter(IMainView mainView, Model model) {
        return new MainPresenter(mainView, model);
    }

    @Override
    public ITaskEditPresenter createTaskDetailPresenter(IAdvancedTask task) {
        return new TaskEditPresenter(task);
    }

    @Override
    public DelegatingPresenter initializeDelegatingPresenter(Context context) {
        return new DelegatingPresenter(context);
    }
    @Override
    public DelegatingPresenter initializeDelegatingPresenter() {
        return new DelegatingPresenter();
    }


    private PresenterFactory(){}
}
