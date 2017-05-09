package cbstudios.coffeebreak.controller;

import android.net.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import cbstudios.coffeebreak.eventBus.StatisticEvent;
import cbstudios.coffeebreak.model.AchievementConverter;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.StatisticsConverter;
import cbstudios.coffeebreak.model.TaskConverter;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.storage.StorageUtil;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

/**
 * @author Felix, Elias, Zack
 * @version 0.1
 */
class MainPresenter implements IMainPresenter {
    private Model model;
    private IMainView mainView;
    private ITaskAdapter taskAdapter;


    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCreate() {
        model = new Model();
        loadTasks();
        loadStatistics();
        loadAchievements();
    }

    @Override
    public void onPause() {
        saveTasks();
        saveStatistics();
        saveAchievements();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

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

    private void loadAchievements(){
        JsonElement element = StorageUtil.load(mainView.getAppCompatActivity().getApplicationContext(), "Create");

        if (element == null || !element.isJsonObject()) {
            try {
                JsonParser parser = new JsonParser();

                JsonArray jsonArray = (JsonArray) parser.parse(new FileReader("C:\\coffeebreak\\Application\\CoffeeBreak\\app\\src\\main\\java\\cbstudios\\coffeebreak\\model\\tododatamodule\\statistics\\achievements\\AchievementColletion.Json"));


                List<IAchievement> achievements = AchievementConverter.getInstance().toAchievementList(jsonArray);

                model.getToDoDataModule().getAchievementList().setCreateTaskAchievementsList(achievements);
            } catch (FileNotFoundException e) {
                EventBus.getDefault().post(new StatisticEvent("Updated"));
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return;
        }
        JsonArray array = element.getAsJsonArray();

        List<IAchievement> achievements = AchievementConverter.getInstance().toAchievementList(array);

        model.getToDoDataModule().getAchievementList().setCreateTaskAchievementsList(achievements);
    }

    private void saveAchievements(){
        JsonArray array = AchievementConverter.getInstance().toJsonArray(model.getToDoDataModule().getAchievementList().getCreateTaskAchievementsList());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Create", array);
        JsonArray array2 = AchievementConverter.getInstance().toJsonArray(model.getToDoDataModule().getAchievementList().getCheckTaskAchievementsList());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Check", array2);
        JsonArray array3 = AchievementConverter.getInstance().toJsonArray(model.getToDoDataModule().getAchievementList().getTimesAppOpenAchievementList());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Started", array3);
    }

    private void saveStatistics() {
        JsonObject object = StatisticsConverter.getInstance().toJsonObject(model.getToDoDataModule().getStats());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Statistics", object);
    }
    private void loadStatistics() {
        JsonElement element = StorageUtil.load(mainView.getAppCompatActivity().getApplicationContext(), "Statistics");

        if (element == null || !element.isJsonObject()){
            return;
        }
        JsonObject object = element.getAsJsonObject();

        Statistics statistics = StatisticsConverter.getInstance().toStatistics(object);

        model.getToDoDataModule().setStatistic(statistics);
    }

    private void loadTasks() {
        JsonElement element = StorageUtil.load(mainView.getAppCompatActivity().getApplicationContext(), "Tasks");

        if (element == null || !element.isJsonArray())
            return;

        JsonArray array = element.getAsJsonArray();
        List<IAdvancedTask> tasks = TaskConverter.getInstance().toList(array);

        for (IAdvancedTask task : tasks) {
            model.getToDoDataModule().addTask(task);
        }
    }

    private void saveTasks() {
        JsonArray array = TaskConverter.getInstance().toJsonArray(model.getToDoDataModule().getTasks());
        StorageUtil.save(mainView.getAppCompatActivity().getApplicationContext(), "Tasks", array);
    }

    public Model getModel(){
        return this.model;
    }
}
