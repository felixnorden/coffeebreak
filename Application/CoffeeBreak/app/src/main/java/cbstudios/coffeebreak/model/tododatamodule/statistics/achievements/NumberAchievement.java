package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

import cbstudios.coffeebreak.model.tododatamodule.statistics.IAchievementStatistics;

/**
 * @author Johan
 * @version 1.2
 *          <p>Responsibility: To check if the int NumberLimit is reached
 *          and set itself as completed</br >
 *          Uses: IAchievement </br>
 *          Used by: Statistics
 *          </p>
 *
 */
public class NumberAchievement implements IAchievement {

    public static final int TASK_CREATED = 0;
    public static final int TASK_CHECKED = 1;
    public static final int TIMES_UPDATED = 2;
    public static final int TIMES_APP_STARTED = 3;
    public static final int TIMES_NAV_OPEN = 4;
    public static final int TIMES_TASK_DELETED = 5;
    public static final int TIMES_CATEGORY_CREATED = 6;
    public static final int TIMES_SETTINGS_CHANGED = 7;
    public static final int TASKS_ALIVE = 8;
    public static final int DAYS_IN_A_ROW = 9;

    private int type;

    private String name;
    private int numberLimit;
    private Boolean isCompleted;

    /**
     * Class constructor
     *
     * @param name        is the name of the achievement
     * @param numberLimit is the number of times a specific assigment has to done
     *                    in order to complete the achievement
     */
    public NumberAchievement(String name, int numberLimit, int type) {
        this.name = name;
        this.numberLimit = numberLimit;
        this.isCompleted = false;
        this.type = type;
    }

    /**
     * Check which achievement should be checked if completed
     * @param stats is the apps statistics
     */
    public void update(IAchievementStatistics stats) {
        switch (this.getType()) {
            case (TASK_CREATED):
                checkIfCompleted(stats.getCreatedTasks());
                break;
            case (TASK_CHECKED):
                checkIfCompleted(stats.getCheckOffTasks());
                break;
            case (DAYS_IN_A_ROW):
                checkIfCompleted(stats.getDaysInARow());
                break;
            case (TASKS_ALIVE):
                checkIfCompleted(stats.getTasksAlive());
                break;
            case (TIMES_APP_STARTED):
                checkIfCompleted(stats.getTimesAppStarted());
                break;
            case (TIMES_CATEGORY_CREATED):
                checkIfCompleted(stats.getTimesCategoryCreated());
                break;
            case (TIMES_NAV_OPEN):
                checkIfCompleted(stats.getTimesNavOpen());
                break;
            case (TIMES_SETTINGS_CHANGED):
                checkIfCompleted(stats.getTimesSettingsChanged());
                break;
            case (TIMES_TASK_DELETED):
                checkIfCompleted(stats.getTimesTaskDeleted());
                break;
            case (TIMES_UPDATED):
                checkIfCompleted(stats.getTimesUpdated());
                break;
            default:
                break;
        }
        ;
    }

    public NumberAchievement() {

    }

    /**
     * @return the achievement numberLimit int
     */
    public int getNumberLimit() {
        return this.numberLimit;
    }

    /**
     * sets the name of achievement
     *
     * @param name is the name of the achievement
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the numberLimit in the achievement
     *
     * @param numberLimit is an int that represent how many times a specific thing must be done in
     *                    order to complete the achievement
     */
    public void setNumberLimit(int numberLimit) {
        this.numberLimit = numberLimit;
    }

    /**
     * @return the name of the achievement
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the achievement is completed by comparing the achievement numberLimit and another int
     *
     * @param number is the number of times a specific thing has been done
     */
    public void checkIfCompleted(int number) {
        if (number >= numberLimit) {
            isCompleted = true;
        }
    }

    /**
     * set the achievement to completed
     */
    @Override
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * @return the achievements boolean isCompleted
     */
    @Override
    public boolean getIfCompleted() {
        return isCompleted;
    }

    /**
     * @return an int that is unique from different objects
     */
    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    /**
     * An equals method for the class
     *
     * @param o is the object that the method will compare to
     * @return True if equal, false if not equal
     */
    @Override
    public boolean equals(Object o) {
        return false;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
