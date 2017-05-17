package cbstudios.coffeebreak.controller;

import java.util.Calendar;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

public interface ITaskDetailPresenter extends IPresenter {
    String getTaskName();

    void setTaskName(String name);

    Calendar getNotificationCalendar();

    void setNotificationCalendar(Calendar cal);
}