package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */

public class AchievementsFactory implements IAchievementsFactory{
    private static final IAchievementsFactory achievementsFactory = new AchievementsFactory();

    public static IAchievementsFactory getInstance() {
        return achievementsFactory;
    }

    @Override
    public numberAchievments createNumberAchievements(String name, int numberLimit) {
        return new numberAchievments(name, numberLimit);
    }



    @Override
    public timeAcvhievements createTimeAchievements(String name) {
        return new timeAcvhievements(name);
    }
}
