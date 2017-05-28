package cbstudios.coffeebreak.model.tododatamodule.categorylist;////



import java.util.List;

/**
 * @author Johan
 * @version 1.0
 *          <p>Responsibility: Abstraction interface for CategoryList </br >
 *          Used by: ToDoDataModule
 *          </p>
 *
 */
public interface ICategoryList {

    /**
     * Add a new Label Category without a name
     */
    void addLabelCategory();

    /**
     * Add a new Label category
     * @param name of the Label Category
     */
    void addLabelCategory(String name);

    /**
     * Adds the category to the model.
     * @param category The category to be added.
     */
    void addLabelCategory(ILabelCategory category);

    /**
     * Remove an existing Label Category with labelCategory object
     * @param labelCategory is the Label category that will be removed
     */
    void removeLabelCategory(ILabelCategory labelCategory);

    /**
     * Remove an existing Label Category with the name of the label category
     * @param name of the Label Category that will be removed
     */
    void removeLabelCategory(String name);

    /**
     * Returns the ILabelCategory with the given name. If the category doesn't exist
     * it is created, added to list, and returned.
     * @param name The name of the category.
     * @return The ILabelCategory found/created.
     */
    ILabelCategory getLabelCategory(String name);

    /**
     * Getter for the Time Categories list
     * @return the list with all Time Categories
     */
    List<ITimeCategory> getTimeCategories();

    /**
     * Getter for the Label Categories list
     * @return the list with all Label Categories
     */
    List<ILabelCategory> getLabelCategories();

}
