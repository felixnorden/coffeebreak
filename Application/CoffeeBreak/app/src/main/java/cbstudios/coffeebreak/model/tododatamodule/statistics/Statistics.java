package cbstudios.coffeebreak.model.tododatamodule.statistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private int daysInARow;
    private Calendar lastDayCheckedTask;


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

        daysInARow = 0;
        lastDayCheckedTask = null;

        achievementList = new ArrayList<>();
        InitAchievement();
    }

    public void onEvent(String name){
        switch (name) {
            case "Create":
                createdTasks++;
                tasksAlive++;
                checkAchievement(name, createdTasks);
                checkAchievement("TasksAlive", tasksAlive);
                break;
            case "Check":
                checkOffTasks++;
                tasksAlive--;
                checkAchievement(name, checkOffTasks);
                
                if(lastDayCheckedTask == null) {
                    lastDayCheckedTask = Calendar.getInstance();
                    daysInARow++;
                    return;
                }
                Calendar now = Calendar.getInstance();
                
                if((now.get(Calendar.YEAR) == lastDayCheckedTask.get(Calendar.YEAR))){
                    if((now.get(Calendar.DAY_OF_YEAR))-(lastDayCheckedTask.get(Calendar.DAY_OF_YEAR)) ==1){
                        lastDayCheckedTask = now;
                        daysInARow++;
                    } else if ((now.get(Calendar.DAY_OF_YEAR))-(lastDayCheckedTask.get(Calendar.DAY_OF_YEAR)) > 1){
                        lastDayCheckedTask = now;
                        daysInARow = 0;
                    }
                }

                checkAchievement("DaysInARow", daysInARow);


                break;
            case "TimesUpdated":
                timesUpdated++;
                checkAchievement(name, timesUpdated);
                break;
            case "TimesAppStarted":
                timesAppStarted++;
                checkAchievement(name, timesAppStarted);
                break;
            case "TimesNavOpen":
                timesNavOpen++;
                checkAchievement(name, timesNavOpen);
                break;
            case "TimesTaskDeleted":
                timesTaskDeleted++;
                tasksAlive--;
                checkAchievement(name, timesTaskDeleted);
                break;
            case "TimesCategoryCreated":
                timesCategoryCreated++;
                checkAchievement(name, timesCategoryCreated);
                break;
            case "TimesSettingsChanged":
                timesSettingsChanged++;
                checkAchievement(name, timesSettingsChanged);
                break;
        }
    }

    private void checkAchievement(String name, int stats) {
        for (int i = 0; i < achievementList.size(); i++){
            if(achievementList.get(i).getName().equals(name)){
                if(!(achievementList.get(i).getIfCompleted())){
                    achievementList.get(i).checkIfCompleted(stats);
                }
            }
        }
    }

    public void InitAchievement(){
        //int[] array = new int[]{5,25,100,500};
        int[] array = new int[]{2,5,20,25};

        for (int i = 0; i < array.length; i++) {
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("Create", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("Check", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesUpdated", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesAppStarted", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesNavOpen", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesTaskDeleted", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesCategoryCreated", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesSettingsChanged", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("TasksAlive", array[i]));
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements("DaysInARow", array[i]));

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
