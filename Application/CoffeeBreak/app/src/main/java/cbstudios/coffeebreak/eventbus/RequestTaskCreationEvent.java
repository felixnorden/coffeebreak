package cbstudios.coffeebreak.eventbus;

/**
 * Created by Felix on 2017-05-23.
 */

public class RequestTaskCreationEvent {
    public static final int ADVANCED_TASK = 0;
    public static final int LIST_TASK = 1;

    public final int type;

    public RequestTaskCreationEvent(int type){
        this.type = type;
    }
}
