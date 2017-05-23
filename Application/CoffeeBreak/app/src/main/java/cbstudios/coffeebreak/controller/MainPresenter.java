package cbstudios.coffeebreak.controller;

import android.app.usage.UsageEvents;
import android.content.Intent;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileNotFoundException;
import java.util.List;

import cbstudios.coffeebreak.eventbus.CheckTaskEvent;
import cbstudios.coffeebreak.eventbus.CreateTaskEvent;
import cbstudios.coffeebreak.eventbus.EditTaskEvent;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStartEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.RemovePresenterEvent;
import cbstudios.coffeebreak.eventbus.RequestTaskCreationEvent;
import cbstudios.coffeebreak.eventbus.SaveStateEvent;
import cbstudios.coffeebreak.eventbus.TimesAppStartedEvent;
import cbstudios.coffeebreak.eventbus.TimesCategoryCreatedEvent;
import cbstudios.coffeebreak.eventbus.TimesNavOpenEvent;
import cbstudios.coffeebreak.eventbus.TimesSettingsChangedEvent;
import cbstudios.coffeebreak.eventbus.TimesTaskDeletedEvent;
import cbstudios.coffeebreak.eventbus.TimesUpdatedEvent;
import cbstudios.coffeebreak.model.AchievementConverter;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.StatisticsConverter;
import cbstudios.coffeebreak.model.TaskConverter;
import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.util.StorageUtil;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.activity.TaskEditActivity;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

/**
 * @author Felix, Elias, Zack
 * @version 0.1
 */
class MainPresenter extends BasePresenter implements IMainPresenter {
    private IMainView mainView;
    private ITaskAdapter taskAdapter;

    // TODO: 2017-05-18 Gather presenters in List somewhere
    private ITaskEditPresenter taskEditPresenter;

    MainPresenter(IMainView mainView, Model model) {
        this.model = model;
        this.mainView = mainView;
        taskAdapter = new TaskAdapter(mainView.getAppCompatActivity(), this);

        EventBus.getDefault().register(this);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if(event.object instanceof IMainView) {
            mainView = (IMainView) event.object;
            mainView.setCategories(model.getToDoDataModule().getLabelCategories(), model.getToDoDataModule().getTimeCategories());
            mainView.setTaskAdapter(taskAdapter);

            // TODO Check later when All-category is implemented
            taskAdapter.updateTasks();
            taskAdapter.filterTasks();

            mainView.setNavDrawer();
            mainView.setToolbar();
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        //TODO Fix shiet
        if(event.object == mainView) {
            taskAdapter.updateTasks();
            taskAdapter.filterTasks();

            EventBus.getDefault().post(new SaveStateEvent());
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onResume(OnResumeEvent event) {

    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onDestroy(OnDestroyEvent event) {
        if(event.object == mainView) {
            EventBus.getDefault().unregister(this);
            collectDeadPresenter();
        }
    }

    private void collectDeadPresenter() {
        detachView();
        EventBus.getDefault().post(new RemovePresenterEvent(this));
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onStop(OnStopEvent event) {
        if(event.object == mainView) {
            registerViewComponents(false);
//            System.out.println("Unregister");
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onStart(OnStartEvent event) {
        if(event.object == mainView) {
            registerViewComponents(true);
//            System.out.println("Register");
        }
    }

    @Override
    public void injectModel(Model model) {
        this.model = model;
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTaskCreationRequest(RequestTaskCreationEvent event){
        switch(event.type){
            case ADVANCED_TASK: createAdvancedTask();
                break;
            case LIST_TASK: createListTask();
                break;
            default: break;
        }
    }

    // TODO: 2017-05-23 Make sure that these methods are hidden.
    @Override
    public void createAdvancedTask() {
        model.getToDoDataModule().createAdvancedTask();
    }

    @Override
    public void createListTask() {
        model.getToDoDataModule().createListTask();
    }

    @Override
    public void createTask(IListTask listTask) {
        model.getToDoDataModule().createTask(listTask);
    }

    @Override
    public void removeTask(IAdvancedTask task) {
        model.getToDoDataModule().removeTask(task);
        EventBus.getDefault().post(new CheckTaskEvent());
    }

    @Override
    public List<IAdvancedTask> getTasks() {
        return model.getToDoDataModule().getTasks();
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }

    public void updateView(IMainView view){
        mainView = view;
    }
    /*@Override
    public List<ILabelCategory> getLabelCategories() {
        return model.getToDoDataModule().getLabelCategories();
    }

    @Override
    public List<ITimeCategory> getTimeCategories() {
        return model.getToDoDataModule().getTimeCategories();
    }*/


    @Subscribe
    public void onEditTaskEvent(EditTaskEvent event) {
        taskEditPresenter = PresenterFactory.getInstance().createTaskDetailPresenter(event.getTask());
        Intent intent = new Intent(mainView.getAppCompatActivity(), TaskEditActivity.class);
        mainView.getAppCompatActivity().startActivity(intent);
    }

    @Subscribe
    public void onCreateTaskEvent(CreateTaskEvent event) {
        model.getToDoDataModule().getStats().onCreateTaskEvent();
    }

    @Subscribe
    public void onCheckTaskEvent(CheckTaskEvent event){
        model.getToDoDataModule().getStats().onCheckTaskEvent();
    }

    @Subscribe
    public void onTimesAppStartedEvent(TimesAppStartedEvent event) {
        model.getToDoDataModule().getStats().onTimesAppStartedEvent();
    }

    @Subscribe
    public void onTimesCategoryCreated(TimesCategoryCreatedEvent event) {
        model.getToDoDataModule().getStats().onTimesCategoryCreated();
    }

    @Subscribe
    public void onTimesNavOpenEvent(TimesNavOpenEvent event) {
        model.getToDoDataModule().getStats().onTimesNavOpenEvent();
    }

    @Subscribe
    public void onTimesSettingsChangedEvent(TimesSettingsChangedEvent event) {
        model.getToDoDataModule().getStats().onTimesSettingsChangedEvent();
    }

    @Subscribe
    public void onTimesTaskDeletedEvent(TimesTaskDeletedEvent event){
        model.getToDoDataModule().getStats().onTimesTaskDeletedEvent();
    }

    @Subscribe
    public void onTimesUpdatedEvent(TimesUpdatedEvent event){
        model.getToDoDataModule().getStats().onTimesUpdatedEvent();
    }

    public Model getModel(){
        return model;
    }

    private void registerViewComponents(boolean value){
        if(value) {
            EventBus.getDefault().register(mainView);
            EventBus.getDefault().register(taskAdapter);
        }
        else{
            EventBus.getDefault().unregister(mainView);
            EventBus.getDefault().unregister(taskAdapter);
        }
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

    private void saveAchievements() {
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


