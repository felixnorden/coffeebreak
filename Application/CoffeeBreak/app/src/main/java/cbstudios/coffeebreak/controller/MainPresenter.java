package cbstudios.coffeebreak.controller;

import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cbstudios.coffeebreak.eventbus.CategoryDeletedEvent;
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
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.view.activity.AchievementActivity;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

/**
 * @author Felix
 * @version 2.0
 *          <p>Responsibility: Handling all communication between the main view {@link cbstudios.coffeebreak.view.activity.MainActivity}
 *          and the {@link Model} and the MainActivity's inherent components. </br >
 *          Uses: {@link Model}, {@link IMainView}, {@link ITaskAdapter}, {@link TaskSorter}
 *          </br>
 *          Events:
 *          <ul>
 *              Activity Events
 *              <li>{@link RequestTaskCreationEvent}</li>
 *              <li>{@link RemoveTaskEvent}</li>
 *              <li>{@link RequestTaskListEvent}</li>
 *              <li>{@link EditTaskEvent}</li>
 *              <li>{@link }</li>
 *              <li>{@link SortListEvent}</li>
 *          </ul>
 *          Used by:
 *          </p>
 *
 */
class MainPresenter extends BasePresenter implements IMainPresenter {
    private IMainView mainView;
    private ITaskAdapter taskAdapter;
    private IAchievementPresenter achievementPresenter;

    MainPresenter(IMainView mainView, Model model) {
        this.model = model;
        this.mainView = mainView;
        taskAdapter = new TaskAdapter(mainView.getAppCompatActivity(), getTasks());

        EventBus.getDefault().register(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        //TODO Fix shiet
        if(event.object == mainView) {
            taskAdapter.refreshItems(mainView.getCurrentCategory());

            EventBus.getDefault().post(new SaveStateEvent());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onResume(OnResumeEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onDestroy(OnDestroyEvent event) {
        if(event.object == mainView) {
            EventBus.getDefault().unregister(this);
            collectDeadPresenter();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onStop(OnStopEvent event) {
        if(event.object == mainView) {
            registerViewComponents(false);
//            System.out.println("Unregister");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onStart(OnStartEvent event) {
        if(event.object == mainView) {
            registerViewComponents(true);
//            System.out.println("Register");
        }
    }

    /**
     * Sorts the tasks in a specific order
     * @param event containing sorting order
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onSortingOrderChange(SortListEvent event){
        TaskSorter sorter = TaskSorter.getInstance();
        List<IAdvancedTask> tasks = getTasks();
        switch (event.order){
            case SortListEvent.ORDERING_ALPHABETICAL:
                sorter.sortAlphabetically(tasks);
                model.getToDoDataModule().setTasks(tasks);
                taskAdapter.swapTasks(mainView.getCurrentCategory().getValidTasks(tasks));
                break;
            case SortListEvent.ORDERING_CHRONOLOGICAL:
                sorter.sortChronologically(tasks);
                model.getToDoDataModule().setTasks(tasks);
                taskAdapter.swapTasks(mainView.getCurrentCategory().getValidTasks(tasks));
                break;
            case SortListEvent.ORDERING_PRIORITY:
                sorter.sortPriorities(tasks);
                model.getToDoDataModule().setTasks(tasks);
                taskAdapter.swapTasks(mainView.getCurrentCategory().getValidTasks(tasks));
                break;
            default: return;
        }
    }

    /**
     * Sorts the tasks in a specific order
     * @param event containing sorting order
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onCategoryDeleted(CategoryDeletedEvent event){
        if(event.which == 0){
        List<ILabelCategory> categoryList = model.getToDoDataModule().getLabelCategories();
        categoryList.remove(event.position);
        }
        mainView.updateNavDrawerList();
    }

    /**
     * Injects the dependant model
     * @param model
     */
    @Override
    public void injectModel(Model model) {
        this.model = model;
    }

    /**
     * Creates either an AdvancedTask or a ListTask
     * depending on the requested type
     * @param event holding the type-id for the task to be created
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTaskCreationRequest(RequestTaskCreationEvent event){
        EventBus.getDefault().post(new CreateTaskEvent());
        switch(event.type){
            case ADVANCED_TASK: createAdvancedTask();
                break;
            case LIST_TASK: createListTask();
                break;
            default: break;
        }
    }

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

    /**
     * Removes the requested task from the list
     * @param event contains the task to remove
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void removeTask(RemoveTaskEvent event) {
        model.getToDoDataModule().removeTask(event.task);
        if(event.checked){
            EventBus.getDefault().post(new CheckTaskEvent());
        }
    }

    /**
     * Updates the temporary list of tasks in the {@link TaskAdapter}
     * @param event
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onTaskListRequest(RequestTaskListEvent event){
        event.adapter.updateTmpTasks(getTasks());
        //taskAdapter.updateTmpTasks(getTasks());
    }

    /**
     * Requests a new LabelCategory to be created with the containing string.
     * @param event holding the name of the {@link cbstudios.coffeebreak.model.tododatamodule.categorylist.LabelCategory}
     */
    @Subscribe
    public void onCreateCategoryEvent(CreateCategoryEvent event){
        model.getToDoDataModule().addLabelCategory();
        EventBus.getDefault().post(new TimesCategoryCreatedEvent());
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
    @Subscribe
    public void onCreateTaskEvent(CreateTaskEvent event) {
        model.getToDoDataModule().getStats().onCreateTaskEvent();
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
    @Subscribe
    public void onCheckTaskEvent(CheckTaskEvent event){
        model.getToDoDataModule().getStats().onCheckTaskEvent();
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
    @Subscribe
    public void onTimesAppStartedEvent(TimesAppStartedEvent event) {
        model.getToDoDataModule().getStats().onTimesAppStartedEvent();
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
    @Subscribe
    public void onTimesCategoryCreated(TimesCategoryCreatedEvent event) {
        model.getToDoDataModule().getStats().onTimesCategoryCreated();
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
    @Subscribe
    public void onTimesNavOpenEvent(TimesNavOpenEvent event) {
        model.getToDoDataModule().getStats().onTimesNavOpenEvent();
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
    @Subscribe
    public void onTimesSettingsChangedEvent(TimesSettingsChangedEvent event) {
        model.getToDoDataModule().getStats().onTimesSettingsChangedEvent();
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
    @Subscribe
    public void onTimesTaskDeletedEvent(TimesTaskDeletedEvent event){
        model.getToDoDataModule().getStats().onTimesTaskDeletedEvent();
    }

    /**
     * Notifies model's statistics of an update request
     * @param event the type of update to be made
     */
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


    // Frees up presenter of its view and requests to be garbage collected
    private void collectDeadPresenter() {
        detachView();
        EventBus.getDefault().post(new RemovePresenterEvent(this));
    }

    private List<IAdvancedTask> getTasks() {
        return model.getToDoDataModule().getTasks();
    }
    
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void showAchievementActivity(TimesTaskDeletedEvent event){
        achievementPresenter = new AchievementPresenter(model);
        Intent intent = new Intent(mainView.getAppCompatActivity(), AchievementActivity.class);
        mainView.getAppCompatActivity().startActivity(intent);
    }

}


