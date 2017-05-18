package cbstudios.coffeebreak.eventbus;

import java.util.Objects;

/**
 * Created by Felix on 2017-05-18.
 */

public class OnStartEvent {
    public final Object object;

    public OnStartEvent(Object o){
        object = o;
    }
}
