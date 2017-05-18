package cbstudios.coffeebreak.controller;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import cbstudios.coffeebreak.eventbus.EditTaskActivityEvent;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.ITaskDetailView;

public class TaskDetailPresenter extends BasePresenter implements ITaskDetailPresenter {

    private ITaskDetailView view;
    private IAdvancedTask task;

    TaskDetailPresenter(ITaskDetailView taskDetailView) {
        this.view = taskDetailView;
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop(){
        EventBus.getDefault().unregister(this);
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
}