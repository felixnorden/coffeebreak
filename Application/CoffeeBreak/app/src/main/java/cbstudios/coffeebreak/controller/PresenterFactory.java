package cbstudios.coffeebreak.controller;

import android.content.Context;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.IAchievementView;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.ITaskEditView;

/**
 * @author Felix, Elias
 * @version 1.0
 *          <p>Responsibility: Singleton Factory to create instances of presenters for their corresponding activities</br>
 *          Uses: MainPresenter, AchievementPresenter and TaskEditPresenter.</br>
 *          Used by: DelegatingPresenter to create presenters on activity requests.
 *          </p>
 */

public class PresenterFactory implements IPresenterFactory {
    private final static IPresenterFactory INSTANCE = new PresenterFactory();

    /**
     * @return singleton instance of the factory
     */
    public static IPresenterFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public MainPresenter createMainPresenter(IMainView mainView, Model model) {
        return new MainPresenter(mainView, model);
    }

    @Override
    public TaskEditPresenter createTaskEditPresenter(IAdvancedTask task, Model model) {
        return new TaskEditPresenter(task, model);
    }

    @Override
    public DelegatingPresenter initializeDelegatingPresenter(Context context) {
        return new DelegatingPresenter(context);
    }

    public AchievementPresenter createAchievementPresenter(Model model) {
        return new AchievementPresenter(model);
    }

    /**
     * Hidden constructor because singleton.
     */
    private PresenterFactory() {
    }
}
