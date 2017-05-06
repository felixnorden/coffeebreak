package cbstudios.coffeebreak.model.Achievements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 5/6/2017.
 */

/**
 * A class that holds all lists with different achievments
 */
public class AchievementsList implements IAchievementsList {
    public final List<numberAchievments> createTaskAchievmentsList;
    public final List<numberAchievments> checkTaskAchievmentsList;
    public final List<timeAchievements> timeAcvhievementsList;

    /**
     * The class constructor that will init the lists to arraylists
     */
    public AchievementsList(){
        createTaskAchievmentsList = new ArrayList<>();
        checkTaskAchievmentsList = new ArrayList<>();
        timeAcvhievementsList = new ArrayList<>();
    }

    /**
     * Creates all number achievements and adds them to different lists
     */
    public void initNumberAchievements(){
        int[] number = new int[]{1,10,25,50,100};
        for (int i : number){
            createTaskAchievmentsList.add(AchievementsFactory.getInstance().createNumberAchievements("Create", i));
            checkTaskAchievmentsList.add(AchievementsFactory.getInstance().createNumberAchievements("Check",i));
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
