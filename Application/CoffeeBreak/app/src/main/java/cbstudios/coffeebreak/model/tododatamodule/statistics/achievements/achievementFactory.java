package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */

/**
 * A factory class for achievements that will create all new achievements
 */
public class achievementFactory implements IAchievementFactory {
    private static final IAchievementFactory achievementsFactory = new achievementFactory();

    /**
     *
     * @return a static AchievmentsFactory
     */
    public static IAchievementFactory getInstance() {
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
    public numberAchievement createNumberAchievements(String name, int numberLimit) {
        return new numberAchievement(name, numberLimit);
    }

    /**
     * Creates a new timeAchievement
     * @param name is the name of the achievement
     * @return a new timeAchievement
     */
    @Override
    public timeAchievement createTimeAchievements(String name) {
        return new timeAchievement(name);
    }
}