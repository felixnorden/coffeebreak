package cbstudios.coffeebreak.eventbus;


import android.view.View;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * Created by elias on 25/05/2017.
 */

public class CategoryKeyboardClosedEvent implements IEvent {
    public final View view;
    public final int position;
    public final boolean removeTask;
    public final ILabelCategory labelCategory;

    public CategoryKeyboardClosedEvent(View view, int position, boolean removeTask, ILabelCategory labelCategory) {
        this.view = view;
        this.position = position;
        this.removeTask = removeTask;
        this.labelCategory = labelCategory;
    }
}
