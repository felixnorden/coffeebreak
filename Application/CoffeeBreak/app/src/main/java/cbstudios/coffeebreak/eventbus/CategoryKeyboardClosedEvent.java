package cbstudios.coffeebreak.eventbus;


import android.view.View;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * @author Elias
 * @version 1.0
 *          <p>Responsibility: Send event when the keyboard needs to be closed after creating a category </br >
 *          Used by: MainPresenter, MainActivity
 *          </p>
 */

public class CategoryKeyboardClosedEvent implements IEvent {
    public final View view;
    public final int position;
    public final boolean removeTask;
    public final ILabelCategory labelCategory;

    /**
     *
     * @param view is the current view
     * @param position is the current position
     * @param removeTask is the boolean that checks if a task needs to be removed
     * @param labelCategory is the current labelCategory
     */
    public CategoryKeyboardClosedEvent(View view, int position, boolean removeTask, ILabelCategory labelCategory) {
        this.view = view;
        this.position = position;
        this.removeTask = removeTask;
        this.labelCategory = labelCategory;
    }
}
