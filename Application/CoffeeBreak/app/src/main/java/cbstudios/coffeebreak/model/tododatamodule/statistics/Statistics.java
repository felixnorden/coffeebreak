package cbstudios.coffeebreak.model.tododatamodule.statistics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cbstudios.coffeebreak.eventBus.StatisticEvent;

/**
 * Created by johan on 5/8/2017.
 */

public class Statistics {
    private int createdTasks;
    private int checkOffTasks;
    private int timesUpdated;
    private int timesAppStarted;
    private int timesNavOpen;
    private int timesTaskDeleted;
    private int timesCategoryCreated;
    private int timesSettingsChanged;
    private int tasksAlive;



    public Statistics(){
        createdTasks = 0;
        checkOffTasks = 0;
        timesUpdated = 0;
        timesAppStarted = 0;
        timesNavOpen = 0;
        timesTaskDeleted = 0;
        timesCategoryCreated = 0;
        timesSettingsChanged = 0;
        tasksAlive = 0;

        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void onEvent(StatisticEvent event){
        switch (event.getMessage()) {
            case "Create":
                createdTasks++;
                break;
            case "Check":
                checkOffTasks++;
                break;
            case "Updated":
                timesUpdated++;
                break;
            case "appStarted":
                timesAppStarted++;
                break;
            case "NavOpen":
                timesNavOpen++;
                break;
            case "TaskDeleted":
                timesTaskDeleted++;
                break;
            case "CategoryCreated":
                timesCategoryCreated++;
                break;
            case "SettingsChanged":
                timesSettingsChanged++;
                break;
        }
    }

    public int getCreatedTasks() {
        return createdTasks;
    }

    public int getCheckOffTasks() {
        return checkOffTasks;
    }

    public int getTimesUpdated() {
        return timesUpdated;
    }

    public int getAppStarted() {
        return timesAppStarted;
    }

    public int getTimesNavOpen() {
        return timesNavOpen;
    }

    public int getTimesTaskDeleted() {
        return timesTaskDeleted;
    }

    public int getTimesCategoryCreated() {
        return timesCategoryCreated;
    }

    public int getTimesSettingsChanged() {
        return timesSettingsChanged;
    }

    public int getTasksAlive() {
        return tasksAlive;
    }

    public void setCreatedTasks(int createdTasks) {
        this.createdTasks = createdTasks;
    }

    public void setCheckOffTasks(int checkOffTasks) {
        this.checkOffTasks = checkOffTasks;
    }

    public void setTimesUpdated(int timesUpdated) {
        this.timesUpdated = timesUpdated;
    }

    public void setAppStarted(int timesAppStarted) {
        this.timesAppStarted = timesAppStarted;
    }

    public void setTimesNavOpen(int timesNavOpen) {
        this.timesNavOpen = timesNavOpen;
    }

    public void setTimesTaskDeleted(int timesTaskDeleted) {
        this.timesTaskDeleted = timesTaskDeleted;
    }

    public void setTimesCategoryCreated(int timesCategoryCreated) {
        this.timesCategoryCreated = timesCategoryCreated;
    }

    public void setTimesSettingsChanged(int timesSettingsChanged) {
        this.timesSettingsChanged = timesSettingsChanged;
    }

    public void setTasksAlive(int tasksAlive) {
        this.tasksAlive = tasksAlive;
    }

}
