package cbstudios.coffeebreak.model.tododatamodule.categorylist;
//

//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.CategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * A class that holds both Lists of Categories
 */

public class CategoryList implements ICategoryList {

    private final ICategory AllCategory;
    private final List<ILabelCategory> labelCategories;
    private final List<ITimeCategory> timeCategories;

    /**
     * The constructor that will create two lists.
     */
    public CategoryList() {
        AllCategory = new AllCategory();
        labelCategories = new ArrayList<>();
        timeCategories = new ArrayList<>();
        initTimeCategories();
    }

    /**
     * Add a new Label Category without a name
     */
    public void addLabelCategory() {
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory());
    }

    /**
     * Adds a new labelCategory to the labelCategory list.
     * @param name the name of the labelCategory
     */
    public void addLabelCategory(String name) {
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory(name));
    }

    /**
     * Remove a labelCategory from the labelCategory list.
     * @param label labelCategory that will be removed
     */
    public void removeLabelCategory(ILabelCategory label) {
        labelCategories.remove(label);
    }

    /**
     * Remove a labelCategory from the labelCategory list.
     * @param name the name of the labelCategory that will be removed.
     */
    public void removeLabelCategory(String name) {
        for(ILabelCategory label : labelCategories){
            if(Objects.equals(label.getName(), name)){
                labelCategories.remove(label);
            }
        }
    }

    /**
     *
     * @return the timeCategories list
     */
    public List<ITimeCategory> getTimeCategories() {
        return timeCategories;
    }

    /**
     * Creates all the timeCategories.
     */
    private void initTimeCategories() {
        timeCategories.add(CategoryFactory.getInstance().createAllCategory());
        Calendar currentDate = Calendar.getInstance();
        timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Tomorrow", tomorrow));
        Calendar week = Calendar.getInstance();
        week.add(Calendar.DATE, 6);
        timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("Next 7 days", week));
        Calendar month = Calendar.getInstance();
        month.add(Calendar.DATE, 30);
        timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("Next 30 days", month));
    }
    /**
     *
     * @return the labelCategories list.
     */
    public List<ILabelCategory> getLabelCategories() {
        return labelCategories;
    }

    /**
     * An equals method for the class
     * @param o is the object that the method will compare to
     * @return True if equal, false if not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryList that = (CategoryList) o;

        if (labelCategories != null ? !labelCategories.equals(that.getLabelCategories()): that.getLabelCategories() != null ) return false;
        return timeCategories != null ? timeCategories.equals(that.getTimeCategories()) : that.getTimeCategories() == null;

    }

    /**
     *
     * @return an int that is unique from different objects
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (labelCategories != null ? labelCategories.hashCode() : 0);
        result = 31 * result + (timeCategories != null ? timeCategories.hashCode() : 0);

        return result;
    }
}

