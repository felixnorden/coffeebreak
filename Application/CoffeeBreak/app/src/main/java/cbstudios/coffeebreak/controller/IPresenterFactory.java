package cbstudios.coffeebreak.controller;

import android.content.Context;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.ITaskEditView;

/**
 * @author Felix
 * @version 1.1
 *          <p>Responsibility: Interface for the {@link PresenterFactory}</br >
 *          Uses: {@link IMainView}, {@link Model},
 *          {@link IAdvancedTask} and {@link Context} </br>
 *          Used by: {@link DelegatingPresenter} and
 *          {@link cbstudios.coffeebreak.view.activity.MainActivity} on startup
 *          </p>
 *
 */
public interface IPresenterFactory {

    /**
     * Creates an instance of {@link MainPresenter}
     * @param mainView the view that the presenter will communicate with
     * @param model the model keeping all data
     * @return  the new presenter through a {@link IMainPresenter} reference
     */
    IMainPresenter createMainPresenter(IMainView mainView, Model model);

    /**
     * Creates an instance of {@link TaskEditPresenter}
     * @param task the task that has been clicked on by the user.
     * @return  the new presenter through a {@link ITaskEditPresenter} reference
     */
    ITaskEditPresenter createTaskDetailPresenter(IAdvancedTask task);

    /**
     * Creates an instance of {@link DelegatingPresenter}
     * @param context the view that the presenter will communicate with
     * @return  the new presenter through a {@link DelegatingPresenter} reference
     */
    DelegatingPresenter initializeDelegatingPresenter(Context context);
}
