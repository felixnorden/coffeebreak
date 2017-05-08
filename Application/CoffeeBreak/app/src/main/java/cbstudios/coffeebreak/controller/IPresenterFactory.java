package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;

/**
 * Created by Lenovo on 2017-05-08.
 */

public interface IPresenterFactory {
    IMainPresenter createMainPresenter(IMainView mainView, ITaskAdapter taskAdapter);
}
