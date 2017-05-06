package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */

/**
 * Interface for all achievements
 */
public interface IAchievements {

    /**
     *
     * @return a String with the achievements name
     */
    String getName();

    /**
     * Set the achievement to completed by changing a boolean to true
     */
    void setCompleted();


}
