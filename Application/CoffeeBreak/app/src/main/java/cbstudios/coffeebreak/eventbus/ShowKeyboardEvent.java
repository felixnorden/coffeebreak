package cbstudios.coffeebreak.eventbus;

/**
 * @author Felix
 * @version 1.0
 */

public class ShowKeyboardEvent {
    public final boolean showKeyboard;

    public ShowKeyboardEvent(boolean showKeyboard){
        this.showKeyboard = showKeyboard;
    }
}
