package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;

public class AchievementEvent implements IEvent {
    private final IAchievement achievement;

    public AchievementEvent(IAchievement achievement) {
        this.achievement = achievement;
    }

    public IAchievement getAchievement() {
        return achievement;
    }
}
