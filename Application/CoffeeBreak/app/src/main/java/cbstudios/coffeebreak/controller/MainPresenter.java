package cbstudios.coffeebreak.controller;

import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cbstudios.coffeebreak.eventbus.CheckTaskEvent;
import cbstudios.coffeebreak.eventbus.CreateCategoryEvent;
import cbstudios.coffeebreak.eventbus.CreateTaskEvent;
import cbstudios.coffeebreak.eventbus.EditTaskEvent;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStartEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.RemovePresenterEvent;
import cbstudios.coffeebreak.eventbus.RemoveTaskEvent;
import cbstudios.coffeebreak.eventbus.RequestTaskCreationEvent;
import cbstudios.coffeebreak.eventbus.RequestTaskListEvent;
import cbstudios.coffeebreak.eventbus.SaveStateEvent;
import cbstudios.coffeebreak.eventbus.SortListEvent;
import cbstudios.coffeebreak.eventbus.TimesAppStartedEvent;
import cbstudios.coffeebreak.eventbus.TimesCategoryCreatedEvent;
import cbstudios.coffeebreak.eventbus.TimesNavOpenEvent;
import cbstudios.coffeebreak.eventbus.TimesSettingsChangedEvent;
import cbstudios.coffeebreak.eventbus.TimesTaskDeletedEvent;
import cbstudios.coffeebreak.eventbus.TimesUpdatedEvent;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.TaskSorter;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
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
        taskAdapter = new TaskAdapter(mainView.getAppCompatActivity(), getTasks());

        EventBus.getDefault().register(this);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if(event.object instanceof IMainView) {
            mainView = (IMainView) event.object;
            mainView.setCategories(model.getToDoDataModule().getLabelCategories(), model.getToDoDataModule().getTimeCategories());
            mainView.setTaskAdapter(taskAdapter);
            mainView.setCurrentCategory(model.getToDoDataModule().getTimeCategories().get(0));
            taskAdapter.refreshItems(mainView.getCurrentCategory());

            mainView.setNavDrawer();
            mainView.setToolbar();
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        //TODO Fix shiet
        if(event.object == mainView) {
            taskAdapter.refreshItems(mainView.getCurrentCategory());

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

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onSortingOrderChange(SortListEvent event){
        TaskSorter sorter = TaskSorter.getInstance();
        List<IAdvancedTask> tasks = getTasks();
        switch (event.order){
            case SortListEvent.ORDERING_ALPHABETICAL:
                sorter.sortAlphabetically(tasks);
                model.getToDoDataModule().sortTasks(tasks);
                taskAdapter.swapTasks(mainView.getCurrentCategory().getValidTasks(tasks));
                break;
            case SortListEvent.ORDERING_CHRONOLOGICAL:
                sorter.sortChronologically(tasks);
                model.getToDoDataModule().sortTasks(tasks);
                taskAdapter.swapTasks(mainView.getCurrentCategory().getValidTasks(tasks));
                break;
            case SortListEvent.ORDERING_PRIORITY:
                sorter.sortPriorities(tasks);
                model.getToDoDataModule().sortTasks(tasks);
                taskAdapter.swapTasks(mainView.getCurrentCategory().getValidTasks(tasks));
                break;
            default: return;
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

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void removeTask(RemoveTaskEvent event) {
        model.getToDoDataModule().removeTask(event.task);
        if(event.checked){
            EventBus.getDefault().post(new CheckTaskEvent());
        }
    }

    public List<IAdvancedTask> getTasks() {
        return model.getToDoDataModule().getTasks();
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTaskListRequest(RequestTaskListEvent event){
        event.adapter.updateTmpTasks(getTasks());
    }
    @Subscribe
    public void onEditTaskEvent(EditTaskEvent event) {
        taskEditPresenter = PresenterFactory.getInstance().createTaskDetailPresenter(event.getTask());
        taskEditPresenter.injectModel(this.model);
        Intent intent = new Intent(mainView.getAppCompatActivity(), TaskEditActivity.class);
        mainView.getAppCompatActivity().startActivity(intent);
    }

    @Subscribe
    public void onCreateCategoryEvent(CreateCategoryEvent event){
        model.getToDoDataModule().addLabelCategory("Mock1");

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

    private void detachView() {
        this.mainView = null;
    }

}


