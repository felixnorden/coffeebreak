package cbstudios.coffeebreak.eventbus;

import android.app.Activity;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when a new Query needs to be registered </br >
 *          Used by: MainPresenter, MainActivity
 *          </p>
 */

public class QueryRegistrationEvent {
    public final boolean register;
    public final Activity activity;

    /**
     *
     * @param register true if it will be registered, false if not
     * @param activity is the current activity
     */
    public QueryRegistrationEvent(boolean register, Activity activity){
        this.register = register;
        this.activity = activity;
    }
}
