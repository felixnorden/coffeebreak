package cbstudios.coffeebreak.eventbus;

import android.content.Context;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when context is updated</br >
 *          Used by: DelegatingPresenter, MainActivity
 *          </p>
 */

public class UpdateContextReferenceEvent {
    public final Context context;

    /**
     *
     * @param context is context
     */
    public UpdateContextReferenceEvent(Context context){
        this.context = context;
    }
}
