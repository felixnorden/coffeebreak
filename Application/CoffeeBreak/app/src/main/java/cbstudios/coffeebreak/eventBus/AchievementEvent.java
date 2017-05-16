package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;

/**
 * Created by johan on 5/10/2017.
 */

public class AchievementEvent {
    private final IAchievement achievement;

    public AchievementEvent(IAchievement achievement){
        this.achievement = achievement;
    }

    public IAchievement getAchievement(){
        return achievement;
    }
}
