package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * @author Johan
 * @version 1.0
 *          <p>Responsibility: Abstraction interface for achievementFactory</br >
 *          Used by: Statistics
 *          </p>
 *
 */
public interface IAchievementFactory {

    /**
     *
     * @param name is the name of the achievement
     * @param numberLimit is the number that of times a specific assigment has to done
     *                    in order to complete the achievement
     * @param type is a int needed for a switch case
     * @return a new numberAchievement
     */
    NumberAchievement createNumberAchievements(String name, int numberLimit, int type);

}
