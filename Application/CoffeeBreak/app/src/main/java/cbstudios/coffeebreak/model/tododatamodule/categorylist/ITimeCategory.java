package cbstudios.coffeebreak.model.tododatamodule.categorylist;//

import java.util.Calendar;

/**
 * @author Elias, Johan
 * @version 1.0
 *          Responsibility: Abstracting interface for categories using time to filter tasks
 *          Uses:
 *          Used by: AllCategory, AchievementActivity, CategoryFactory, CategoryList, IAchievementView,
 *          ICategoryFactory, ICategoryList, IMainView, MainActivity, MultipleDayTimeCategory.
 */
public interface ITimeCategory extends ICategory {
    /**
     * A method that checks if a specific date is within the intervall of the timeCategory
     *
     * @param date is the date that is checked if it is within the intervall
     * @return true if it is within the intervall, false if it is not
     */
    boolean isInIntervall(Calendar date);

    /**
     * @return the date that a timeCategory holds
     */
    Calendar getTime();

    /**
     * An equals method that compares a timeCategory with another object to check if they are
     * the same
     *
     * @param o is the object the timeCategory will compare with
     * @return true if equal, false if not equal
     */
    boolean equals(Object o);

    /**
     * @return an int that is unique from different objects
     */
    int hashCode();
}
