package cbstudios.coffeebreak.view.activity;


import android.widget.ArrayAdapter;

import java.util.Calendar;

import cbstudios.coffeebreak.view.adapter.TaskEditCategoryAdapter;

/**
 * @author Zack
 * @version 1.0
 *          <p>Responsibility: Abstracting interface for {@link cbstudios.coffeebreak.controller.TaskEditPresenter}'s interaction
 *          with its {@link cbstudios.coffeebreak.view.activity.TaskEditActivity}</br >
 *          Used by: {@link cbstudios.coffeebreak.controller.TaskEditPresenter} </br>
 *          Implemented by: {@link cbstudios.coffeebreak.view.activity.TaskEditActivity}
 *          </p>
 */
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
     * Setups the label list with correct adapter
     *
     * @param adapter The correct adapter
     */
    void setCategoriesAdapter(TaskEditCategoryAdapter adapter);

    /**
     * Setups the autocomplete field with correct adapter
     *
     * @param adapter The ArrayAdapter
     */
    void setAutoCompleteAdapter(ArrayAdapter<String> adapter);

    /**
     * Notify listview that model has changed.
     */
    void notifyCategoriesChanged();

    /**
     * Returns the current priority in a string defined in string.xml
     *
     * @return Priority.
     */
    String getPriority();

    /**
     * Sets the view to show the given priority.
     *
     * @param priority The string which represents the priority
     * @param color    The color of the priority.
     */
    void setPriority(String priority, int color);

    /**
     * @return Note currently in view
     */
    String getNote();

    /**
     * Sets note in view.
     *
     * @param note Note from task.
     */
    void setNote(String note);
}
