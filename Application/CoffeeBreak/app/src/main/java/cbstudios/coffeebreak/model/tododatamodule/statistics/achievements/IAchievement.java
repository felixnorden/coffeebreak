package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */

/**
 * Interface for all achievements
 */
public interface IAchievement {

    /**
     *
     * @return a String with the achievements name
     */
    String getName();

    /**
     * Set the achievement to completed by changing a boolean to true
     */
    void setIsCompleted(boolean isCompleted);

    /**
     * @return the achievements numberLimit
     */
    int getNumberLimit();

    /**
     * Checks if the achievement is completed and sets the boolean true if it is, false if not
     * @param number
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
    public void setNumberLimit(int numberLimit);

    /**
     * sets the name of achievement
     * @param name is the name of the achievement
     */
    public void setName(String name);

}
