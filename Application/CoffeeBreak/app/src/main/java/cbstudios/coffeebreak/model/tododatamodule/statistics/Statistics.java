package cbstudios.coffeebreak.model.tododatamodule.statistics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cbstudios.coffeebreak.eventbus.StatisticEvent;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.AchievementList;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievementList;

/**
 * Created by johan on 5/8/2017.
 */

public class Statistics {

    private IAchievementList achievementList;

    //private List<IAchievement> achievementList;

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
        achievementList = new AchievementList();
        achievementList.InitAchievement();
        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void onEvent(StatisticEvent event){
        switch (event.getMessage()) {
            case "Create":
                createdTasks++;
                tasksAlive++;
                achievementList.getCreateTaskAchievementsList().get(0).checkIfCompleted(createdTasks, achievementList.getCreateTaskAchievementsList(), 0);
                //achievementList.getTaskAliveAchievementList().get(0).checkIfCompleted(tasksAlive);
                break;
            case "Check":
                checkOffTasks++;
                tasksAlive--;
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

    public int getTimesAppStarted() {
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

    public IAchievementList getAchievementList() {
        return achievementList;
    }

}
