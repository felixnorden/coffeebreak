package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */


/**
 * Interface for the achievement factory class
 */
public interface IAchievementsFactory {

    /**
     *
     * @param name is the name of the achievement
     * @param numberLimit is the number that of times a specific assigment has to done
     *                    in order to complete the achievement
     * @return a new numberAchievement
     */
    numberAchievments createNumberAchievements(String name, int numberLimit);

    /**
     *
     * @param name is the name of the achievement
     * @return a new timeAchievement
     */
    timeAchievements createTimeAchievements(String name);
}
