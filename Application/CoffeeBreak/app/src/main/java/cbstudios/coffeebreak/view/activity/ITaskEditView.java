package cbstudios.coffeebreak.view.activity;

import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

public interface ITaskEditView extends IView {
    /**
     * Returns the string in the name-field.
     *
     * @return The name-field in the view.
     */
    String getNameText();

    /**
     * @param text Sets the name-field in the view.
     */
    void setNameText(String text);

    /**
     * Set ups the view to reflect the calendar date.
     *
     * @param cal The calendar to reflect.
     */
    void setNotification(Calendar cal);

    /**
     * Returns the modified calendar from the view.
     *
     * @return The modified calendar.
     */
    Calendar getNotification();

    /**
     * Sets the title in the toolbar.
     *
     * @param title The string to show as title.
     */
    void setTitle(String title);

    /**
     * Returns current text in new-category-text-field and resets it to null.
     *
     * @return Current string found in categoriesAddText-field.
     */
    String getNewLabelText();

    /**
     * Setups the categories with a custom-adapter.
     *
     * @param list List of ILabelCategories from task
     */
    void setupCategories(List<ILabelCategory> list);

    /**
     * Notify listview that model has changed.
     */
    void notifyCategoriesChanged();
}
