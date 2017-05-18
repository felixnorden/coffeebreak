package cbstudios.coffeebreak.controller;

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
    public IMainPresenter createMainPresenter(IMainView mainView) {
        return new MainPresenter(mainView);
    }

    @Override
    public ITaskEditPresenter createTaskDetailPresenter(ITaskEditView taskDetailView) {
        return new TaskEditPresenter(taskDetailView);
    }


    private PresenterFactory(){}
}
