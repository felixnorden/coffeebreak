package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */

import cbstudios.coffeebreak.model.tododatamodule.statistics.IAchievementStatistics;

/**
 * @author Johan
 * @version 1.1
 *          <p>Responsibility: Abstraction interface for numberAchievement </br >
 *          Used by: Statistics, AchievementAdapter, AchievementConverter, DelegatingPresenter, NumberAchievement
 *          </p>
 *
 */
public interface IAchievement {

    /**
     * Updates all achievement to see if they are completed
     * @param stats is all the statistics
     */
    void update(IAchievementStatistics stats);

    /**
     *
     * @return a String with the achievements name
     */
    String getName();

    /**
     * Set the achievement to completed by changing a boolean to true
     * @param isCompleted is true if the achievement will be set completed, false if not
     */
    void setIsCompleted(boolean isCompleted);

    /**
     * @return the achievements numberLimit
     */
    int getNumberLimit();

    /**
     * Checks if the achievement is completed and sets the boolean true if it is, false if not
     * @param number is the number representing the achievements statistic
     */
    void checkIfCompleted(int number);

    /**
     * @return the boolean in the achievement. True if it is completed and false if it is not.
     */
    boolean getIfCompleted();

    /**
     * sets the numberLimit in the achievement
     * @param numberLimit is an int that represent how many times a specific thing must be done in
     *                    order to complete the achievement
     */
    void setNumberLimit(int numberLimit);

    /**
     * sets the name of achievement
     * @param name is the name of the achievement
     */
    void setName(String name);

    int getType();

    void setType(int i);

}
