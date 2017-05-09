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
    private List<IAchievement> timesAppOpenAchievementList;

    /**
     * The class constructor that will init the lists to arraylists
     */
    public AchievementList(){
        createTaskAchievementsList = new ArrayList<>();
        checkTaskAchievementsList = new ArrayList<>();
        timesAppOpenAchievementList = new ArrayList<>();
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
}
