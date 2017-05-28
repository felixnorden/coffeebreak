package cbstudios.coffeebreak.eventbus;

import android.view.View;

/**
 * @author Felix
 * @version 1.0
 */

public class ShowKeyboardEvent implements IEvent {
    public final boolean showKeyboard;
    public final View view;

    /**
     *
     * @param showKeyboard is the keyBoard that will be showed
     * @param view is the current view
     */
    public ShowKeyboardEvent(boolean showKeyboard, View view) {
        this.showKeyboard = showKeyboard;
        this.view = view;
    }
}
