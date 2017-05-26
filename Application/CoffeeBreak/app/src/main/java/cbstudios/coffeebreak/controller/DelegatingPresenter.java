package cbstudios.coffeebreak.controller;

import android.content.Context;
import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.eventbus.EditTaskEvent;
import cbstudios.coffeebreak.eventbus.RemovePresenterEvent;
import cbstudios.coffeebreak.eventbus.RequestPresenterEvent;
import cbstudios.coffeebreak.eventbus.SaveStateEvent;
import cbstudios.coffeebreak.eventbus.UpdateContextReferenceEvent;
import cbstudios.coffeebreak.model.AchievementConverter;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.StatisticsConverter;
import cbstudios.coffeebreak.model.TaskConverter;
import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.util.StorageUtil;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.ITaskEditView;
import cbstudios.coffeebreak.view.activity.MainActivity;
import cbstudios.coffeebreak.view.activity.TaskEditActivity;

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

    DelegatingPresenter(Context mContext) {
        this.model = new Model();
        presenters = new ArrayList<>();
        this.mContext = mContext;

        EventBus.getDefault().register(this);
        loadTasks();
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
        if (event.context instanceof IMainView)
            this.mContext = event.context;
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
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void saveState(SaveStateEvent event) {
        saveTasks();
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
        if (event.view == mContext) {
            IPresenter mainPresenter = factory.createMainPresenter((IMainView) mContext, model);
            presenters.add(mainPresenter);
        }
    }

    /**
     * Handles launching an EditTaskActivity
     *
     * @param event
     */
    @Subscribe
    public void onEditTaskEvent(EditTaskEvent event) {
        IPresenter taskEditPresenter = PresenterFactory.getInstance().createTaskDetailPresenter(event.getTask(), model);
        presenters.add(taskEditPresenter);
        Intent intent = new Intent(mContext, TaskEditActivity.class);
        mContext.startActivity(intent);
    }

    private void loadAchievements() {
        JsonElement element = null;
        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Achievement");
        } catch (IllegalStateException e) {
            // TODO: 2017-05-11 Proper error handling. Notify user of corrupt data somehow?
            StorageUtil.resetData(mContext.getApplicationContext(), "Tasks");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (element == null || !element.isJsonArray()) {
            return;
        }
        JsonArray array = element.getAsJsonArray();

        List<IAchievement> achievements = AchievementConverter.getInstance().toAchievementList(array);
        model.getToDoDataModule().getStats().setAchievementList(achievements);
    }

    private void saveAchievements() {
        JsonArray array = AchievementConverter.getInstance().toJsonArray(model.getToDoDataModule().getStats().getAchievementList());
        StorageUtil.save(mContext.getApplicationContext(), "Achievement", array);
    }

    private void saveStatistics() {
        JsonObject object = StatisticsConverter.getInstance().toJsonObject(model.getToDoDataModule().getStats());
        StorageUtil.save(mContext.getApplicationContext(), "Statistics", object);
    }

    private void loadStatistics() {
        JsonElement element = null;
        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Statistics");
        } catch (IllegalStateException e) {
            // TODO: 2017-05-11 Proper error handling. Notify user of corrupt data somehow?
            StorageUtil.resetData(mContext.getApplicationContext(), "Statistics");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (element == null || !element.isJsonObject()) {
            return;
        }
        JsonObject object = element.getAsJsonObject();

        Statistics statistics = StatisticsConverter.getInstance().toStatistics(object);

        model.getToDoDataModule().setStatistic(statistics);
    }

    private void loadTasks() {
        JsonElement element;

        try {
            element = StorageUtil.load(mContext.getApplicationContext(), "Tasks");

            if (element == null || !element.isJsonArray())
                return;

            JsonArray array = element.getAsJsonArray();
            List<IAdvancedTask> tasks = TaskConverter.getInstance().toList(array);

            for (IAdvancedTask task : tasks) {
                model.getToDoDataModule().addTask(task);
            }
        } catch (IllegalStateException e) {
            // TODO: 2017-05-11 Proper error handling. Notify user of corrupt data somehow?
            StorageUtil.resetData(mContext.getApplicationContext(), "Tasks");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveTasks() {
        JsonArray array = TaskConverter.getInstance().toJsonArray(model.getToDoDataModule().getTasks());
        StorageUtil.save(mContext.getApplicationContext(), "Tasks", array);
    }
}
