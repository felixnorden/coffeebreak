package cbstudios.coffeebreak.controller;

import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cbstudios.coffeebreak.eventbus.EditTaskActivityEvent;
import cbstudios.coffeebreak.eventbus.EditTaskEvent;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.ITaskDetailView;
import cbstudios.coffeebreak.view.activity.TaskDetailActivity;

public class TaskDetailPresenter extends BasePresenter implements ITaskDetailPresenter {

    private ITaskDetailView view;
    private IAdvancedTask task;

    TaskDetailPresenter(ITaskDetailView taskDetailView) {
        this.view = taskDetailView;
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        System.out.println("Regged for event");
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEditTaskActivityEvent(EditTaskActivityEvent event) {
        System.out.println("EditTaskActivityEvent!! - " + event.getTask().getName());
        this.task = event.getTask();
        view.setNameText(task.getName());
    }
}
