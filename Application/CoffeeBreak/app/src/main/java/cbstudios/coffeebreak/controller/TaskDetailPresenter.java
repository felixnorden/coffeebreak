package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.view.activity.ITaskDetailView;

public class TaskDetailPresenter extends BasePresenter implements ITaskDetailPresenter {

    private ITaskDetailView view;

    public TaskDetailPresenter(ITaskDetailView taskDetailView){
        this.view = taskDetailView;
    }
}
