package cbstudios.coffeebreak.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStartEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.TaskEditedEvent;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.ITaskEditView;
import cbstudios.coffeebreak.view.activity.TaskEditActivity;

public class TaskEditPresenter extends BasePresenter implements ITaskEditPresenter {

    private ITaskEditView view;
    private final IAdvancedTask task;

    TaskEditPresenter(IAdvancedTask task) {
        this.task = task;
        EventBus.getDefault().register(this);
    }

    @Override
    public void injectModel(Model model) {
        this.model = model;
    }

    @Subscribe
    public void onTaskEdited(TaskEditedEvent event) {
        updateModel();
    }

    @Override
    public String getTaskName() {
        return task.getName();
    }

    @Override
    public void setTaskName(String name) {
        task.setName(name);
    }

    @Override
    public Calendar getNotificationCalendar() {
        return task.getDate();
    }

    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void setNotificationCalendar(Calendar cal) {
        task.setDate(cal);
        view.setNotification(cal);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof ITaskEditView) {
            view = (ITaskEditView) event.object;
            setupView();
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        if (event.object instanceof ITaskEditView) {

        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onResume(OnResumeEvent event) {
        if (event.object instanceof ITaskEditView) {

        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onDestroy(OnDestroyEvent event) {
        if (event.object instanceof ITaskEditView) {

        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onStop(OnStopEvent event) {
        if (event.object instanceof ITaskEditView) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onStart(OnStartEvent event) {
        if(event.object instanceof ITaskEditView)
            EventBus.getDefault().register(this);
    }

    private void setupView() {
        view.setNameText(task.getName());
        view.setTitle(task.getName());
        view.setNotification(task.getDate());
    }

    /**
     * Updates model with current data from view.
     * Calls setupView() to make sure view correctly reflects new model-state.
     */
    private void updateModel() {
        task.setName(view.getNameText());
        task.setDate(view.getNotification());
        setupView();
    }
}