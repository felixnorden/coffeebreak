package cbstudios.coffeebreak.model.tododatamodule.statistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.AchievementFactory;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;

/**
 * Created by johan on 5/8/2017.
 */

/**
 * A class that holds all statistic in the app
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

    private boolean created;

    /**
     * The class constructor that will init alla ints and call for initAchievement
     */
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

    /**
     * Adds 1 to createdTask and adds 1 to tasksAlive
     */
    public void onCreateTaskEvent() {
        createdTasks++;
        tasksAlive++;
    }

    /**
     * Adds 1 to checkOffTask and subtract 1 from tasksAlive, also checks if the int daysInARow should
     * increase or be set to zero
     */
    public void onCheckTaskEvent(){
        checkOffTasks++;
        tasksAlive--;

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

    }

    /**
     * Adds 1 to TimesAppStarted
     */
    public void onTimesAppStartedEvent() {
        timesAppStarted++;
    }

    /**
     * Adds 1 to TimesCategoryCreated
     */
    public void onTimesCategoryCreated() {
        timesCategoryCreated++;
    }

    /**
     * Adds 1 to TimesNavOpen
     */
    public void onTimesNavOpenEvent() {
        timesNavOpen++;
    }

    /**
     * Adds 1 to TimesSettingsChanged
     */
    public void onTimesSettingsChangedEvent() {
        timesSettingsChanged++;
    }

    /**
     * Adds 1 to TimesTaskDeleted
     */
    public void onTimesTaskDeletedEvent(){
        timesTaskDeleted++;
    }

    /**
     * Adds 1 to TimesUpdatedEvent
     */
    public void onTimesUpdatedEvent(){
        timesUpdated++;
    }

    /**
     * Loop through the list and checks if a new achievement is completed
     * @param name is the name of the wanted achievement
     * @param stats is the stats achieved and compared to the achievement
     */
    private void checkAchievement(String name, int stats) {
        for (int i = 0; i < achievementList.size(); i++){
            if(achievementList.get(i).getName().equals(name)){
                if(!(achievementList.get(i).getIfCompleted())){
                    achievementList.get(i).checkIfCompleted(stats);
                }
            }
        }
    }

    /**
     * Initialise all the achievements
     */
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

    public int getDaysInARow(){
        return this.daysInARow;
    }

    public void setDaysInARow(int daysInARow){
        this.daysInARow = daysInARow;
    }

    public Calendar getLastDayCheckedTask(){
        return lastDayCheckedTask;
    }

    public void setLastDayCheckedTask(Calendar lastDayCheckedTask){
        this.lastDayCheckedTask = lastDayCheckedTask;
    }

    public void setAchievementList(List<IAchievement> achievementList) {
        this.achievementList = achievementList;
    }

    public List<IAchievement> getAchievementList() {
        return this.achievementList;
    }

}
