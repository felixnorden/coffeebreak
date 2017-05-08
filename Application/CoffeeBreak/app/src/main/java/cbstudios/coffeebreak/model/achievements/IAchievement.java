package cbstudios.coffeebreak.model.achievements;

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
    void setCompleted();

    /**
     * Check if an achievement is completed
     * @param number is the number of times a specific assigment has to done
     *                    in order to complete the achievement
     * @return
     */
    boolean isCompleted(int number);


}
