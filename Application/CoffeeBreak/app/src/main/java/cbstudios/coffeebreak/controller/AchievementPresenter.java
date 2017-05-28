package cbstudios.coffeebreak.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStartEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.view.activity.IAchievementView;
import cbstudios.coffeebreak.view.adapter.AchievementAdapter;

/**
 * @author Johan
 * @version 2.0
 *          <p>Responsibility: Handling all communication between the AchievementActivity and
 *          and the {@link Model}.
 *          Uses: Model, Events, IAchievementView, AchievementAdapter, IAchievementAdapter.
 *          Used by: BasePresenter, IPresenterFactory, PresenterFactory.
 */

public class AchievementPresenter implements IPresenter {
    private IAchievementView achievementView;
    private Model model;
    private AchievementAdapter achievementAdapter;

    /**
     * Default constructor which needs a model to communicate with.
     *
     * @param model The model to communicate with.
     */
    public AchievementPresenter(Model model) {
        this.model = model;
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof IAchievementView) {
            achievementView = (IAchievementView) event.object;
            achievementView.setCategories(model.getToDoDataModule().getLabelCategories(), model.getToDoDataModule().getTimeCategories());
            achievementView.setNavDrawer();
            achievementView.setToolbar();
            achievementAdapter = new AchievementAdapter(model.getToDoDataModule().getStats().getAchievementList());
            achievementView.setAchievementAdapter(achievementAdapter);

        }
    }

    @Override
    public void onPause(OnPauseEvent event) {

    }

    @Override
    public void onResume(OnResumeEvent event) {

    }

    @Override
    public void onDestroy(OnDestroyEvent event) {

    }

    @Override
    public void onStop(OnStopEvent event) {

    }

    @Override
    public void onStart(OnStartEvent event) {

    }

    @Override
    public void injectModel(Model model) {

    }
}
