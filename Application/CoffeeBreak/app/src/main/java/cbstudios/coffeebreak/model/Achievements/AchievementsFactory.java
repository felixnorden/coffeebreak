package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */

/**
 * A factory class for achievements that will create all new achievements
 */
public class AchievementsFactory implements IAchievementsFactory{
    private static final IAchievementsFactory achievementsFactory = new AchievementsFactory();

    /**
     *
     * @return a static AchievmentsFactory
     */
    public static IAchievementsFactory getInstance() {
        return achievementsFactory;
    }

    /**
     * Creates a new numberAchievement
     * @param name is the name of the achievment
     * @param numberLimit is the number that of times a specific assigment has to done
     *                    in order to complete the achievement
     * @return a new numberAchievement
     */
    @Override
    public numberAchievments createNumberAchievements(String name, int numberLimit) {
        return new numberAchievments(name, numberLimit);
    }

    /**
     * Creates a new timeAchievement
     * @param name is the name of the achievement
     * @return a new timeAchievement
     */
    @Override
    public timeAchievements createTimeAchievements(String name) {
        return new timeAchievements(name);
    }
}
