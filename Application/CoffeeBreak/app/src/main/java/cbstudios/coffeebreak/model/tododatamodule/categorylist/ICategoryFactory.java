package cbstudios.coffeebreak.model.tododatamodule.categorylist;//


import java.util.Calendar;

/**
 * @author Elias
 * @version 1.0
 *          <p>Responsibility: Abstraction interface for categoryFactory </br >
 *          Used by: CategoryList
 *          </p>
 *
 */
public interface ICategoryFactory {

    /**
     * @return a LabelCategory with no name
     */
    ILabelCategory createLabelCategory();

    /**
     * @param name of the new labelCategory
     * @return a new labelCategory with a name
     */
    ILabelCategory createLabelCategory(String name);

    /**
     * @param name  of the new labelCategory
     * @param color The color of the ILabelCategory
     * @return a new labelCategory with a name
     */
    ILabelCategory createLabelCategory(String name, String color);

    /**
     * @param name of the new MultipleDayCategory
     * @param date is the last date of the MultipleDayCategory
     * @return a new MultipleDayCategory with a name and last date
     */
    ITimeCategory createMultipleDayCategory(String name, Calendar date);

    /**
     * @param name of the new SingleDayCategory
     * @param date is the only date of the SingleDayCategory
     * @return a new SingleDayCategory with a name and date
     */
    ITimeCategory createSingleDayCategory(String name, Calendar date);

    ITimeCategory createAllCategory();
}
