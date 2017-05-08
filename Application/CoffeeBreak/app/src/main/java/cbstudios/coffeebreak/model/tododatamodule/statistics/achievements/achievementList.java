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
    public final List<NumberAchievement> createTaskAchievmentsList;
    public final List<NumberAchievement> checkTaskAchievEmentsList;
    public final List<TimeAchievement> timeAcvhievementsList;

    /**
     * The class constructor that will init the lists to arraylists
     */
    public AchievementList(){
        createTaskAchievmentsList = new ArrayList<>();
        checkTaskAchievEmentsList = new ArrayList<>();
        timeAcvhievementsList = new ArrayList<>();
    }

    /**
     * Creates all number achievements and adds them to different lists
     */
    public void initNumberAchievements(){
        int[] number = new int[]{1,10,25,50,100};
        for (int i : number){
            createTaskAchievmentsList.add(AchievementFactory.getInstance().createNumberAchievements("Create", i));
            checkTaskAchievEmentsList.add(AchievementFactory.getInstance().createNumberAchievements("Check",i));
        }

    }

    /**
     * Creates all time achievements and adds them to a list
     */
    public void initTimeAchievements(){

    }

    /**
     * Calls on all init achievements method
     */
    public void initAllAchievements(){
        initNumberAchievements();
        initTimeAchievements();
    }
}
