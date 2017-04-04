package cbstudios.coffeebreak.model.tododatamodule.categorylist;
//

//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.CategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//

import java.util.List;

/**
 * A class that holds both Lists of Categories
 */

public class CategoryList implements ICategoryList {
    private ICategoryFactory categoryFactory;
    private List<ILabelCategory> labelCategories;
    private List<ITimeCategory> timeCategories;

    public void addLabelCategory() {
        labelCategories.add(categoryFactory.createLabelCategory());
    }

    /**
     * Adds a new labelCategory to the labelCategory list.
     * @param name the name of the labelCategory
     */
    public void addLabelCategory(String name) {
        labelCategories.add(categoryFactory.createLabelCategory(name));
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

    /**
     * Creates all the timeCategories.
     */
    public void initTimeCategories() {
        timeCategories.add(categoryFactory.createSingleDayCategory("Today", null));
        timeCategories.add(categoryFactory.createSingleDayCategory("Tomorrow", null));
        timeCategories.add(categoryFactory.createMultipleDayCategory("Next 7 days", null));
        timeCategories.add(categoryFactory.createMultipleDayCategory("Next 30 days", null));
    }

    /**
     *
     * @return the labelCategories list.
     */
    public List<ILabelCategory> getLabelCategories() {
        return labelCategories;
    }
}

