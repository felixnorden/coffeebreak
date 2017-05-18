package cbstudios.coffeebreak.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.TaskEditedEvent;
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

    @Subscribe
    public void onTaskEdited(TaskEditedEvent event) {
        task.setName(view.getNameText());

        task.setDate(view.getNotificationCalendar());
        view.setNotificationCalendar(view.getNotificationCalendar());
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
    @Subscribe
    public void setNotificationCalendar(Calendar cal) {
        task.setDate(cal);
        view.setNotificationCalendar(cal);
    }

    @Override
    @Subscribe
    public void onCreate(OnCreateEvent event) {
        if (event.getObject() instanceof TaskEditActivity) {
            view = (ITaskEditView) event.getObject();
            setupView();
        }
    }

    @Override
    @Subscribe
    public void onPause(OnPauseEvent event) {
        if (event.getObject() instanceof TaskEditActivity) {

        }
    }

    @Override
    @Subscribe
    public void onResume(OnResumeEvent event) {
        if (event.getObject() instanceof TaskEditActivity) {

        }
    }

    @Override
    @Subscribe
    public void onDestroy(OnDestroyEvent event) {
        if (event.getObject() instanceof TaskEditActivity) {

        }
    }

    @Override
    @Subscribe
    public void onStop(OnStopEvent event) {
        if (event.getObject() instanceof TaskEditActivity) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void setupView() {
        view.setNameText(task.getName());
        view.setNotificationCalendar(task.getDate());
    }
}