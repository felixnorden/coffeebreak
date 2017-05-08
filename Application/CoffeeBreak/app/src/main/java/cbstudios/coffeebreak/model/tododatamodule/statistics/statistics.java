package cbstudios.coffeebreak.model.tododatamodule.statistics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cbstudios.coffeebreak.eventBus.statisticEvent;

/**
 * Created by johan on 5/8/2017.
 */

public class statistics {
    int createdTasks;
    int checkOffTasks;
    int timesUpdated;
    int appStarted;
    int timesNavOpen;
    int timesTaskDeleted;
    int timesCategoryCreated;
    int timesSettingsChanged;
    int tasksAlive;



    public statistics(){
        createdTasks = 0;
        checkOffTasks = 0;
        timesUpdated = 0;
        appStarted = 0;
        timesNavOpen = 0;
        timesTaskDeleted = 0;
        timesCategoryCreated = 0;
        timesSettingsChanged = 0;
        tasksAlive = 0;

        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void onEvent(statisticEvent event){
        if(event.getMessage().equals("Create")){
            createdTasks++;
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
        return appStarted;
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

    public void setAppStarted(int appStarted) {
        this.appStarted = appStarted;
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
