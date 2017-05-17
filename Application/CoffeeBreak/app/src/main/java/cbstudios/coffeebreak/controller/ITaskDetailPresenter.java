package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

public interface ITaskDetailPresenter extends IPresenter {
    String getTaskName();
    void setTaskName(String name);
}