package cbstudios.coffeebreak.model.Achievements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 5/6/2017.
 */

public class AchievementsList {
    public final List<numberAchievments> createTaskAchievmentsList;
    public final List<numberAchievments> checkTaskAchievmentsList;
    public final List<timeAcvhievements> timeAcvhievementsList;

    public AchievementsList(){
        createTaskAchievmentsList = new ArrayList<>();
        checkTaskAchievmentsList = new ArrayList<>();
        timeAcvhievementsList = new ArrayList<>();
    }

    public void InitNumberAchievements(){
        int[] number = new int[]{1,10,25,50,100};
        for (int i : number){
            createTaskAchievmentsList.add(AchievementsFactory.getInstance().createNumberAchievements("Create", i));
            checkTaskAchievmentsList.add(AchievementsFactory.getInstance().createNumberAchievements("Check",i));
        }

    }

    public void InitTimeAchievements(){

    }

    public void InitAllAchievements(){
        InitNumberAchievements();
        InitTimeAchievements();
    }
}
