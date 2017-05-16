package cbstudios.coffeebreak.eventbus;

import android.view.View;

/**
 * @author Felix
 * @version 1.0
 */

public class ShowKeyboardEvent {
    public final boolean showKeyboard;
    public final View view;

    public ShowKeyboardEvent(boolean showKeyboard, View view){
        this.showKeyboard = showKeyboard;
        this.view = view;
    }
}
