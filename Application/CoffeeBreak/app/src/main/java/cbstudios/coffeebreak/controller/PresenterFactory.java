package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;

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
    public IMainPresenter createMainPresenter(IMainView mainView, ITaskAdapter taskAdapter) {
        return new MainPresenter(mainView, taskAdapter);
    }

    private PresenterFactory(){}
}
