package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 5/6/2017.
 */

/**
 * A class that holds all lists with different achievments
 */
public class AchievementList implements IAchievementList {
    private List<IAchievement> createTaskAchievementsList;
    private List<IAchievement> checkTaskAchievementsList;
    private List<IAchievement> timesUpdatedAchievementList;
    private List<IAchievement> timesAppOpenAchievementList;
    private List<IAchievement> timesNavOpenAchievementList;
    private List<IAchievement> timesTaskDeletedAchievementList;
    private List<IAchievement> timesCategoryCreatedAchievementList;
    private List<IAchievement> taskAliveAchievementList;

    /**
     * The class constructor that will init the lists to arraylists
     */
    public AchievementList(){
        createTaskAchievementsList = new ArrayList<>();
        checkTaskAchievementsList = new ArrayList<>();
        timesAppOpenAchievementList = new ArrayList<>();
        timesUpdatedAchievementList = new ArrayList<>();
        timesNavOpenAchievementList = new ArrayList<>();
        timesTaskDeletedAchievementList = new ArrayList<>();
        timesCategoryCreatedAchievementList = new ArrayList<>();
        taskAliveAchievementList = new ArrayList<>();
    }

    public void InitAchievement(){
        int[] array = new int[]{2,4,6,8,10,13};
        for (int i = 0; i < array.length; i++) {
            this.createTaskAchievementsList.add(AchievementFactory.getInstance().createNumberAchievements("Create", array[i]));
            this.checkTaskAchievementsList.add(AchievementFactory.getInstance().createNumberAchievements("Check", array[i]));
            this.timesUpdatedAchievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesUpdated", array[i]));
            this.timesAppOpenAchievementList.add(AchievementFactory.getInstance().createNumberAchievements("AppOpen", array[i]));
            this.timesNavOpenAchievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesNavOpen", array[i]));
            this.timesTaskDeletedAchievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesTaskDeleted", array[i]));
            this.timesCategoryCreatedAchievementList.add(AchievementFactory.getInstance().createNumberAchievements("TimesCategoryCreated", array[i]));
            this.taskAliveAchievementList.add(AchievementFactory.getInstance().createNumberAchievements("TasksAlive", array[i]));
        }
    }

    public List<IAchievement> getCreateTaskAchievementsList() {
        return createTaskAchievementsList;
    }

    public void setCreateTaskAchievementsList(List<IAchievement> createTaskAchievementsList){
        this.createTaskAchievementsList = createTaskAchievementsList;
    }

    public List<IAchievement> getCheckTaskAchievementsList() {
        return checkTaskAchievementsList;
    }

    public void setCheckTaskAchievementsList(List<IAchievement> checkTaskAchievementsList){
        this.checkTaskAchievementsList = checkTaskAchievementsList;
    }

    public List<IAchievement> getTimesAppOpenAchievementList() {
        return timesAppOpenAchievementList;
    }

    public void setTimesAppOpenAchievementList(List<IAchievement> timesAppOpenAchievementList) {
        this.timesAppOpenAchievementList = timesAppOpenAchievementList;
    }

    public List<IAchievement> getTimesUpdatedAchievementList() {
        return timesUpdatedAchievementList;
    }

    public void setTimesUpdatedAchievementList(List<IAchievement> timesUpdatedAchievementList) {
        this.timesUpdatedAchievementList = timesUpdatedAchievementList;
    }

    public List<IAchievement> getTimesNavOpenAchievementList() {
        return timesNavOpenAchievementList;
    }

    public void setTimesNavOpenAchievementList(List<IAchievement> timesNavOpenAchievementList) {
        this.timesNavOpenAchievementList = timesNavOpenAchievementList;
    }

    public List<IAchievement> getTimesTaskDeletedAchievementList() {
        return timesTaskDeletedAchievementList;
    }

    public void setTimesTaskDeletedAchievementList(List<IAchievement> timesTaskDeletedAchievementList) {
        this.timesTaskDeletedAchievementList = timesTaskDeletedAchievementList;
    }

    public List<IAchievement> getTimesCategoryCreatedAchievementList() {
        return timesCategoryCreatedAchievementList;
    }

    public void setTimesCategoryCreatedAchievementList(List<IAchievement> timesCategoryCreatedAchievementList) {
        this.timesCategoryCreatedAchievementList = timesCategoryCreatedAchievementList;
    }

    public List<IAchievement> getTaskAliveAchievementList() {
        return taskAliveAchievementList;
    }

    public void setTaskAliveAchievementList(List<IAchievement> taskAliveAchievementList) {
        this.taskAliveAchievementList = taskAliveAchievementList;
    }
}
