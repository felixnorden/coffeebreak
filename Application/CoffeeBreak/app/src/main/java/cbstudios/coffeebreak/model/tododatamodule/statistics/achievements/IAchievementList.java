package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */

/**
 * Interface for Achievement list
 */
public interface IAchievementList {
    /**
     * Creates all number achievements and adds them to different lists
     */
    public void initNumberAchievements();

    /**
     * Creates all time achievements and adds them to a list
     */
    public void initTimeAchievements();

    /**
     * Calls on all init achievements method
     */
    public void initAllAchievements();
}
