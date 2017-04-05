package cbstudios.coffeebreak.model.tododatamodule.categorylist;
//

//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.CategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//

import android.graphics.Color;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A class that holds both Lists of Categories
 */

public class CategoryList implements ICategoryList {

    private List<ILabelCategory> labelCategories;
    private List<ITimeCategory> timeCategories;

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
}

