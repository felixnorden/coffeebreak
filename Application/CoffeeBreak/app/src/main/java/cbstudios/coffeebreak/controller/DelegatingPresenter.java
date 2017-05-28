package cbstudios.coffeebreak.controller;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.eventbus.RemovePresenterEvent;
import cbstudios.coffeebreak.eventbus.RequestPresenterEvent;
import cbstudios.coffeebreak.eventbus.SaveStateEvent;
import cbstudios.coffeebreak.eventbus.UpdateContextReferenceEvent;
import cbstudios.coffeebreak.model.AchievementConverter;
import cbstudios.coffeebreak.model.CategoryConverter;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.util.IListConverter;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.StatisticsConverter;
import cbstudios.coffeebreak.model.TaskConverter;
import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.util.StorageUtil;
import cbstudios.coffeebreak.view.activity.IAchievementView;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.MainActivity;

/**
 * @author Felix
 * @version 1.1
 *          <p>Responsibility: Handling all creation, dependency injection and responsibility delegation
 *          between all presenters and their views.
 *          Also creates the model and handles persistent data management</br >
 *          Uses: {@link IMainView}, {@link Context} {@link IPresenterFactory}
 *          Events:
 *          <ul>
 *          <li>{@link UpdateContextReferenceEvent} </li>
 *          <li>{@link RemovePresenterEvent}</li>
 *          <li>{@link SaveStateEvent}</li>
 *          <li>{@link RequestPresenterEvent}</li>
 *          </ul>
 *          </br>
 *          Used by: {@link MainActivity} during startup for initialization.
 *          </p>
 */

class DelegatingPresenter {
    private Model model;
    private List<IPresenter> presenters;
    private Context mContext;
    private IPresenterFactory factory = PresenterFactory.getInstance();
    private IListConverter<IAdvancedTask> taskConverter = TaskConverter.getInstance();
    private IListConverter<ILabelCategory> categoryConverter = CategoryConverter.getInstance();

    /**
     * Default constructor
     *
     * @param mContext The context of the main activity.
     */
    DelegatingPresenter(Context mContext) {
        this.model = new Model();
        presenters = new ArrayList<>();
        this.mContext = mContext;

        EventBus.getDefault().register(this);
        loadTasks();
        loadCategories();
        loadStatistics();
        loadAchievements();
    }

    /**
     * Updates the main context every time the MainActivity is being recreated,
     * to make sure of persistent data is being managed
     *
     * @param event containing the new context
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateMainContext(UpdateContextReferenceEvent event) {
        if (event.context instanceof IMainView) {
            this.mContext = event.context;
        }
    }

    /**
     * Removes a presenter that has no associated view
     *
     * @param event containing the presenter
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RemoveDeadPresenter(RemovePresenterEvent event) {
        presenters.remove(event.presenter);
    }

    /**
     * Stores the data when request is being made from a view.
     *
     * @param event The event sent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void saveState(SaveStateEvent event) {
        saveTasks();
        saveCategories();
        saveStatistics();
        saveAchievements();
    }

    /**
     * Handles requests for presenter creation for different
     * views
     *
     * @param event containing context for presenter
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPresenterRequest(RequestPresenterEvent event) {
        IPresenter presenter = null;
        if (event.data == mContext) {
            presenter = factory.createMainPresenter((IMainView) mContext, model);

        } else if (event.data instanceof IAdvancedTask) {
            presenter = factory.createTaskEditPresenter((IAdvancedTask) event.data, model);
        } else if (event.data instanceof IAchievementView) {
            presenter = factory.createAchievementPresenter(model);
        }
        presenters.add(presenter);
    }


    /**
     * Loads achievements from previously saved data.
     */
    private void loadAchievements() {
        JsonElement element = null;
        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Achievement");
        } catch (IllegalStateException e) {
            StorageUtil.resetData(mContext.getApplicationContext(), "Achievement");
        } catch (FileNotFoundException e) {
        }

        if (element == null || !element.isJsonArray()) {
            return;
        }
        JsonArray array = element.getAsJsonArray();

        List<IAchievement> achievements = AchievementConverter.getInstance().toObject(array);
        model.getToDoDataModule().getStats().setAchievementList(achievements);
    }

    /**
     * Saves achievements.
     */
    private void saveAchievements() {
        JsonArray array = AchievementConverter.getInstance().toJson(model.getToDoDataModule().getStats().getAchievementList());
        StorageUtil.save(mContext.getApplicationContext(), "Achievement", array);
    }

    /**
     * Saves statistics.
     */
    private void saveStatistics() {
        JsonObject object = StatisticsConverter.getInstance().toJson(model.getToDoDataModule().getStats());
        StorageUtil.save(mContext.getApplicationContext(), "Statistics", object);
    }

    /**
     * Loads statistics from previously saved data.
     */
    private void loadStatistics() {
        JsonElement element = null;
        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Statistics");
        } catch (IllegalStateException e) {
            // TODO: 2017-05-11 Maybe notify user of corrupt data?
            StorageUtil.resetData(mContext.getApplicationContext(), "Statistics");

        } catch (FileNotFoundException ignored) {
        }

        if (element == null || !element.isJsonObject()) {
            return;
        }
        JsonObject object = element.getAsJsonObject();

        Statistics statistics = StatisticsConverter.getInstance().toObject(object);

        model.getToDoDataModule().setStatistic(statistics);
    }

    /**
     * Loads statistics from previously saved data.
     */
    private void loadTasks() {
        JsonElement element;

        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Tasks");

            if (element == null || !element.isJsonArray()) {
                return;
            }

            JsonArray array = element.getAsJsonArray();
            List<IAdvancedTask> tasks = taskConverter.toObject(array);

            for (IAdvancedTask task : tasks) {
                model.getToDoDataModule().addTask(task);
            }
        } catch (IllegalStateException e) {
            StorageUtil.resetData(mContext.getApplicationContext(), "Tasks");
        } catch (FileNotFoundException ignored) {
        }
    }

    /**
     * Saves tasks.
     */
    private void saveTasks() {
        JsonArray array = taskConverter.toJson(model.getToDoDataModule().getTasks());
        StorageUtil.save(mContext.getApplicationContext(), "Tasks", array);
    }

    /**
     * Saves categories.
     */
    private void saveCategories() {
        JsonArray array = categoryConverter.toJson(model.getToDoDataModule().getLabelCategories());
        StorageUtil.save(mContext.getApplicationContext(), "Categories", array);
    }

    /**
     * Loads categories.
     */
    private void loadCategories() {
        JsonElement element;

        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Categories");

            if (element == null || !element.isJsonArray()) {
                return;
            }
            JsonArray array = element.getAsJsonArray();
            List<ILabelCategory> labels = categoryConverter.toObject(array);

            for (ILabelCategory category : labels) {
                model.getToDoDataModule().addILabelCategory(category);
            }
        } catch (IllegalStateException e) {
            StorageUtil.resetData(mContext.getApplicationContext(), "Categories");
        } catch (FileNotFoundException ignored) {
        }
    }
}
