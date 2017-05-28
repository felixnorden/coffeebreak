package cbstudios.coffeebreak.eventbus;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when Task is about to be created </br >
 *          Used by: MainPresenter, MainActivity
 *          </p>
 */


public class RequestTaskCreationEvent {
    public static final int ADVANCED_TASK = 0;
    public static final int LIST_TASK = 1;

    public final int type;

    /**
     *
     * @param type is whih type of task that will be created
     */
    public RequestTaskCreationEvent(int type){
        this.type = type;
    }
}
