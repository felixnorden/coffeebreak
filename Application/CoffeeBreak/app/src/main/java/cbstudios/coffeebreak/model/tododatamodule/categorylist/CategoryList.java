package cbstudios.coffeebreak.model.tododatamodule.categorylist;
//

//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.CategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A class that holds both Lists of Categories
 */

public class CategoryList implements ICategoryList {

    public List<ILabelCategory> labelCategories;
    public List<ITimeCategory> timeCategories;

    public CategoryList() {
        labelCategories = new ArrayList<>();
        timeCategories = new ArrayList<>();
    }


    public void addLabelCategory() {
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory());
    }

    /**
     * Adds a new labelCategory to the labelCategory list.
     * @param name the name of the labelCategory
     */
    public void addLabelCategory(String name, int color) {
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory(name, color));
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
            if(label.getName() == name){
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

    public void initAllCategories() {
        initLabelCategories();
        initTimeCategories();
    }

    /**
     * Creates all the timeCategories.
     */
    public void initTimeCategories() {
        Calendar currentDate = Calendar.getInstance();
        timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        currentDate.add(Calendar.DAY_OF_YEAR, 1);
        timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Tomorrow", currentDate));
        currentDate.add(Calendar.DAY_OF_YEAR, 6);
        timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("Next 7 days", currentDate));
        currentDate.add(Calendar.DAY_OF_YEAR, 23);
        timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("Next 30 days", currentDate));
    }

    /**
     * Creates all the labelCategories
     */
    public void initLabelCategories() {
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Work", Color.BLUE));
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Home", Color.GREEN));
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Meetings", Color.BLACK));

    }
    /**
     *
     * @return the labelCategories list.
     */
    public List<ILabelCategory> getLabelCategories() {
        return labelCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryList that = (CategoryList) o;

        if (labelCategories != null ? !labelCategories.equals(that.getLabelCategories()): that.getLabelCategories() != null ) return false;
        if (timeCategories != null ? !timeCategories.equals(that.getTimeCategories()): that.getTimeCategories() != null ) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (labelCategories != null ? labelCategories.hashCode() : 0);
        result = 31 * result + (timeCategories != null ? timeCategories.hashCode() : 0);

        return result;
    }
}

