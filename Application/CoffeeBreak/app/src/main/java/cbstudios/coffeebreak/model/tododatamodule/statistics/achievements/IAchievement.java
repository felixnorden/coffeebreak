package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */

import java.util.List;

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

    int getNumberLimit();

    void checkIfCompleted(int number, List<IAchievement> achievementList, int i );

    boolean getIfCompleted();

}
