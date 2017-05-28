package cbstudios.coffeebreak.model.tododatamodule.statistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.AchievementFactory;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.NumberAchievement;

/**
 * @author Johan
 * @version 1.2
 *          <p>Responsibility: To hold the apps statistic and achievements </br >
 *          Uses: IAchievement, IAchievementStatistics </br>
 *          Used by: ToDoDataModule
 *          </p>
 *
 */
public class Statistics implements IAchievementStatistics {

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

    public void notifyAllObservers(){
        /*System.out.println("create" + createdTasks);
        System.out.println("check" +checkOffTasks);
        System.out.println("alive" +tasksAlive);
        System.out.println("started" + timesAppStarted);
        System.out.println("cate" +timesCategoryCreated);
        System.out.println("nav" +timesNavOpen);
        System.out.println("days" +daysInARow);
        System.out.println("set" +timesSettingsChanged);
        System.out.println("del" +timesTaskDeleted);
        System.out.println("upd" +timesUpdated);*/

        for (IAchievement achievement : achievementList) {
            achievement.update(this);
        }
    }

    /**
     * Adds 1 to createdTask and adds 1 to tasksAlive
     */
    public void onCreateTaskEvent() {
        createdTasks++;
        tasksAlive++;
        notifyAllObservers();
    }

    /**
     * Adds 1 to checkOffTask and subtract 1 from tasksAlive, also checks if the int daysInARow should
     * increase or be set to zero
     */
    public void onCheckTaskEvent(){
        checkOffTasks++;
        tasksAlive--;
        notifyAllObservers();
        if(lastDayCheckedTask == null) {
            lastDayCheckedTask = Calendar.getInstance();
            daysInARow++;
            return;
        }
        Calendar now = Calendar.getInstance();
        if(now.YEAR == lastDayCheckedTask.YEAR){
            if((now.get(Calendar.DAY_OF_YEAR))-(lastDayCheckedTask.get(Calendar.DAY_OF_YEAR)) == 1){
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
        notifyAllObservers();
    }

    /**
     * Adds 1 to TimesCategoryCreated
     */
    public void onTimesCategoryCreated() {
        timesCategoryCreated++;
        notifyAllObservers();
    }

    /**
     * Adds 1 to TimesNavOpen
     */
    public void onTimesNavOpenEvent() {
        timesNavOpen++;
        notifyAllObservers();
    }

    /**
     * Adds 1 to TimesSettingsChanged
     */
    public void onTimesSettingsChangedEvent() {
        timesSettingsChanged++;
        notifyAllObservers();
    }

    /**
     * Adds 1 to TimesTaskDeleted
     */
    public void onTimesTaskDeletedEvent(){
        timesTaskDeleted++;
        tasksAlive--;
        notifyAllObservers();
    }

    /**
     * Adds 1 to TimesUpdatedEvent
     */
    public void onTimesUpdatedEvent(){
        timesUpdated++;
        notifyAllObservers();
    }

    /**
     * Initialise all the achievements
     */
    public final void InitAchievement(){
        int[] array = new int[]{5,25,100,500};
        int[] array2 = new int[]{1,3,7,10};
        int[] array3 = new int[]{2,14,30,90};

        addToList("Create", array, NumberAchievement.TASK_CREATED);
        addToList("Check", array, NumberAchievement.TASK_CHECKED);
        addToList("TimesUpdated", array, NumberAchievement.TIMES_UPDATED);
        addToList("TimesAppStarted", array, NumberAchievement.TIMES_APP_STARTED);
        addToList("TimesNavOpen", array, NumberAchievement.TIMES_NAV_OPEN);
        addToList("TimesTaskDeleted", array, NumberAchievement.TIMES_TASK_DELETED);
        addToList("TimesCategoryCreated", array2, NumberAchievement.TIMES_CATEGORY_CREATED);
        addToList("TimesSettingsChanged", array2, NumberAchievement.TIMES_SETTINGS_CHANGED);
        addToList("TasksAlive", array, NumberAchievement.TASKS_ALIVE);
        addToList("DaysInARow", array3, NumberAchievement.DAYS_IN_A_ROW);
    }

    public void addToList(String name, int[] arr, int type){
        for (int i = 0; i < arr.length; i++) {
            achievementList.add(AchievementFactory.getInstance().createNumberAchievements(name, arr[i], type));
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
