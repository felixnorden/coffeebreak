package cbstudios.coffeebreak.model.tododatamodule.statistics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.eventbus.StatisticEvent;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.AchievementFactory;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;

/**
 * Created by johan on 5/8/2017.
 */

public class Statistics {

    private List<IAchievement> achievementList;

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
        achievementList = new ArrayList<>();
        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void onEvent(StatisticEvent event){
        switch (event.getMessage()) {
            case "Create":
                createdTasks++;
                tasksAlive++;
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

    public void InitAchievement(){
        //int[] array = new int[]{5,25,100,500};
        int[] array = new int[]{2,5,7,10};

        for (int i = 0; i < array.length; i++) {
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("Create", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("Check", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesUpdated", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("AppOpen", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesNavOpen", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesTaskDeleted", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesCategoryCreated", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TasksAlive", array[i]));
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

    public void setTimesAppStarted(int timesAppStarted) {
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

    public void setAchievementList(List<IAchievement> achievementList) {
        this.achievementList = achievementList;
    }

    public List<IAchievement> getAchievementList() {
        return this.achievementList;
    }

}
