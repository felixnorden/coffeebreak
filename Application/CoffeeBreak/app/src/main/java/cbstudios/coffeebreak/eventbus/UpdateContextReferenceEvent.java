package cbstudios.coffeebreak.eventbus;

import android.content.Context;

/**
 * Created by Felix on 2017-05-23.
 */

public class UpdateContextReferenceEvent {
    public final Context context;

    public UpdateContextReferenceEvent(Context context){
        this.context = context;
    }
}
