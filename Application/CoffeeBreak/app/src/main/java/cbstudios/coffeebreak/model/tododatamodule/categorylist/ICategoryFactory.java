package cbstudios.coffeebreak.model.tododatamodule.categorylist;//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.ICategoryFactory.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author :

import java.util.Calendar;

/**
 * The interface for the CategoryFactory
 */
public interface ICategoryFactory {

    /**
     *
     * @return a LabelCategory with no name
     */
    ILabelCategory createLabelCategory();

    /**
     *
     * @param name of the new labelCategory
     * @return a new labelCategory with a name
     */
    ILabelCategory createLabelCategory(String name);

    /**
     *
     * @param name of the new MultipleDayCategory
     * @param date is the last date of the MultipleDayCategory
     * @return a new MultipleDayCategory with a name and last date
     */
    ITimeCategory createMultipleDayCategory(String name, Calendar date);

    /**
     *
     * @param name of the new SingleDayCategory
     * @param date is the only date of the SingleDayCategory
     * @return a new SingleDayCategory with a name and date
     */
    ITimeCategory createSingleDayCategory(String name, Calendar date);
}
