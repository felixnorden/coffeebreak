package cbstudios.coffeebreak.controller;

import android.app.usage.UsageEvents;
import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.FileNotFoundException;
import java.util.List;

import cbstudios.coffeebreak.eventbus.EditTaskActivityEvent;
import cbstudios.coffeebreak.eventbus.EditTaskEvent;
import cbstudios.coffeebreak.model.AchievementConverter;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.StatisticsConverter;
import cbstudios.coffeebreak.model.TaskConverter;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.util.StorageUtil;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.TaskDetailActivity;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

/**
 * @author Felix, Elias, Zack
 * @version 0.1
 */
class MainPresenter extends BasePresenter implements IMainPresenter {
    private IMainView mainView;
    private ITaskAdapter taskAdapter;


    MainPresenter(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        loadTasks();
        loadStatistics();
        loadAchievements();
        EventBus.getDefault().register(mainView);
        EventBus.getDefault().register(taskAdapter);
    }

    @Override
    public void onPause() {
        //TODO Fix shiet
        taskAdapter.updateTasks();
        taskAdapter.filterTasks();
        saveTasks();
        saveStatistics();
        saveAchievements();

        EventBus.getDefault().unregister(mainView);
        EventBus.getDefault().unregister(taskAdapter);
    }

    @Override
    public void onResume() {
        EventBus.getDefault().register(mainView);
        EventBus.getDefault().register(taskAdapter);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().unregister(mainView);
        EventBus.getDefault().unregister(taskAdapter);
    }

    @Override
    public void setTaskAdapter(TaskAdapter adapter) {
        taskAdapter = adapter;
    }

    @Override
    public void createTask() {
        model.getToDoDataModule().createTask();
    }

    @Override
    public void removeTask(IAdvancedTask task) {
        model.getToDoDataModule().removeTask(task);
    }

    @Override
    public List<IAdvancedTask> getTasks() {
        return model.getToDoDataModule().getTasks();
    }

    @Override
    public List<ILabelCategory> getLabelCategories() {
        return model.getToDoDataModule().getLabelCategories();
    }

    @Override
    public List<ITimeCategory> getTimeCategories() {
        return model.getToDoDataModule().getTimeCategories();
    }

    public void registerComponentsToEventBus(){
        EventBus.getDefault().register(mainView);
        EventBus.getDefault().register(taskAdapter);
    }

    @Subscribe
    public void onEditTaskEvent(EditTaskEvent event) {
        Intent intent = new Intent(mainView.getAppCompatActivity(), TaskDetailActivity.class);
        mainView.getAppCompatActivity().startActivity(intent);

        EventBus.getDefault().postSticky(new EditTaskActivityEvent(event.getTask()));
    }

    private void loadAchievements() {
        JsonElement element = null;
        try {
            element = StorageUtil.load(mainView.getAppCompatActivity().getApplicationContext(), "Achievement");
        } catch (IllegalStateException e) {
            // TODO: 2017-05-11 Proper error handling. Notify user of corrupt data somehow?
            StorageUtil.resetData(mainView.getAppCompatActivity().getApplicationContext(), "Tasks");
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

    private void saveAchievements(){
        JsonArray array = AchievementConverter.getInstance().toJsonArray(model.getToDoDataModule().getStats().getAchievementList());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Achievement", array);
    }

    private void saveStatistics() {
        JsonObject object = StatisticsConverter.getInstance().toJsonObject(model.getToDoDataModule().getStats());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Statistics", object);
    }

    private void loadStatistics() {
        JsonElement element = null;
        try {
            element = StorageUtil.load(mainView.getAppCompatActivity().getApplicationContext(), "Statistics");
        } catch (IllegalStateException e) {
            // TODO: 2017-05-11 Proper error handling. Notify user of corrupt data somehow?
            StorageUtil.resetData(mainView.getAppCompatActivity().getApplicationContext(), "Statistics");
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
            element = StorageUtil.load(mainView.getAppCompatActivity().getApplicationContext(), "Tasks");

            if (element == null || !element.isJsonArray())
                return;

            JsonArray array = element.getAsJsonArray();
            List<IAdvancedTask> tasks = TaskConverter.getInstance().toList(array);

            for (IAdvancedTask task : tasks) {
                model.getToDoDataModule().addTask(task);
            }
        } catch (IllegalStateException e) {
            // TODO: 2017-05-11 Proper error handling. Notify user of corrupt data somehow?
            StorageUtil.resetData(mainView.getAppCompatActivity().getApplicationContext(), "Tasks");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveTasks() {
        JsonArray array = TaskConverter.getInstance().toJsonArray(model.getToDoDataModule().getTasks());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Tasks", array);
    }
}


