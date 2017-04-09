package cbstudios.coffeebreak.model.tododatamodule.categorylist;////
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.ICategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//


import java.util.List;

/**
 * A interface for the class that will hold two types of lists
 */
public interface ICategoryList {

    /**
     * Add a new Label Category without a name
     */
    public void addLabelCategory();

    /**
     * Add a new Label category
     * @param name of the Label Category
     * @param color of the Label Category
     */
    public void addLabelCategory(String name, int color);

    /**
     * Remove an existing Label Category with labelCategory object
     * @param labelCategory is the Label category that will be removed
     */
    public void removeLabelCategory(ILabelCategory labelCategory);

    /**
     * Remove an existing Label Category with the name of the label category
     * @param name of the Label Category that will be removed
     */
    public void removeLabelCategory(String name);

    /**
     * Getter for the Time Categories list
     * @return the list with all Time Categories
     */
    public List<ITimeCategory> getTimeCategories();

    /**
     * Initialize all the time categories
     */
    public void initTimeCategories();

    /**
     * Initialize all the label categories
     */
    public void initLabelCategories();

    /**
     * Initialize all the categories by calling on initTimeCategories and initLabelCategories
     */
    public void initAllCategories();

    /**
     * Getter for the Label Categories list
     * @return the list with all Label Categories
     */
    public List<ILabelCategory> getLabelCategories();

}
