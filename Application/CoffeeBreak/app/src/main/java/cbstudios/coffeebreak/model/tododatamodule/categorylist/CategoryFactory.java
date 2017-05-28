package cbstudios.coffeebreak.model.tododatamodule.categorylist;//


import java.util.Calendar;

/**
 * @author Elias, Johan
 * @version 1.0
 *          <p>Responsibility: Creating all sorts of categories </br >
 *          Uses: ILabelCategory, LabelCategory, ITimeCategory, TimeCategory</br>
 *          Used by: CategoryList
 *          </p>
 *
 */

public class CategoryFactory implements ICategoryFactory {

    private static final ICategoryFactory categoryFactory = new CategoryFactory();

    /**
     * @return the static categoryFactory
     */
    public static ICategoryFactory getInstance() {
        return categoryFactory;
    }

    /**
     * @return a new LabelCategory with no name.
     */
    public ILabelCategory createLabelCategory() {
        return new LabelCategory();
    }

    /**
     * @param name the name of the LabelCategory
     * @return a new LabelCategory with a name.
     */
    @Override
    public ILabelCategory createLabelCategory(String name) {
        return new LabelCategory(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILabelCategory createLabelCategory(String name, String color) {
        return new LabelCategory(name, color);
    }

    /**
     * @param name the name of the MultipleDayTimeCategory
     * @param date the last date for the MultipleDayTimeCategory. All tasks from now until that date will
     *             be placed in this MultipleDayTimeCategory.
     * @return a new MultipleDayTimeCategory
     */
    @Override
    public ITimeCategory createMultipleDayCategory(String name, Calendar date) {
        return new MultipleDayTimeCategory(name, date);
    }

    /**
     * @param name the name of the SingleDayTimeCategory
     * @param date the only date the SingeDayTimeCategory will hold tasks for.
     * @return a new SingleDayTimeCategory
     */
    @Override
    public ITimeCategory createSingleDayCategory(String name, Calendar date) {
        return new SingleDayTimeCategory(name, date);
    }

    public ITimeCategory createAllCategory() {
        return new AllCategory();
    }

}
