package cbstudios.coffeebreak.model.tododatamodule.categorylist;////



/**
 * @author Johan
 * @version 1.0
 *          <p>Responsibility: Abstraction interface for LabelCategory</br >
 *          Used by: CategoryList
 *          </p>
 *
 */
public interface ILabelCategory extends ICategory {
    /**
     * @return the color of the labelCategory
     */
    String getColor();

    /**
     * Sets a new Color to a labelCategory
     *
     * @param color is the new color of a labelCategory
     */
    void setColor(String color);
}