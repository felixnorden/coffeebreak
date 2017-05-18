package cbstudios.coffeebreak.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import cbstudios.coffeebreak.eventbus.EditTaskActivityEvent;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.ITaskEditView;

public class TaskEditPresenter extends BasePresenter implements ITaskEditPresenter {

    private ITaskEditView view;
    private IAdvancedTask task;

    TaskEditPresenter(ITaskEditView taskDetailView) {
        this.view = taskDetailView;
        EventBus.getDefault().register(this);
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEditTaskActivityEvent(EditTaskActivityEvent event) {
        this.task = event.getTask();
        view.setNameText(task.getName());
        view.setNotificationCalendar(task.getDate());
    }

    @Override
    @Subscribe
    public void onCreate(OnCreateEvent event) {

    }

    @Override
    @Subscribe
    public void onPause(OnPauseEvent event) {

    }

    @Override
    @Subscribe
    public void onResume(OnResumeEvent event) {

    }

    @Override
    @Subscribe
    public void onDestroy(OnDestroyEvent event) {

    }

    @Override
    @Subscribe
    public void onStop(OnStopEvent event) {

    }
}