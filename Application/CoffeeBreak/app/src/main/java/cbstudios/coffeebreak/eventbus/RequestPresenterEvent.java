package cbstudios.coffeebreak.eventbus;


/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when a presenter is requested </br >
 *          Used by: MainPresenter, MainActivity, DelegatingPresenter, AchievementActivity, TaskEditActivity
 *          </p>
 */

public class RequestPresenterEvent {
    public final Object data;

    /**
     * Constructor
     * @param data is the current data
     */
    public RequestPresenterEvent(Object data){
        this.data = data;
    }

}
