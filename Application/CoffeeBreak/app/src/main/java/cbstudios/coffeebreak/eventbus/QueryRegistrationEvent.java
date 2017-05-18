package cbstudios.coffeebreak.eventbus;

import android.app.Activity;

/**
 * Created by Lenovo on 2017-05-18.
 */

public class QueryRegistrationEvent {
    public final boolean register;
    public final Activity activity;

    public QueryRegistrationEvent(boolean register, Activity activity){
        this.register = register;
        this.activity = activity;
    }
}
