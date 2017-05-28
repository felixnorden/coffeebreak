package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.view.adapter.ITaskAdapter;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when TaskList is requested </br >
 *          Used by: MainPresenter, TaskAdapter
 *          </p>
 */


public class RequestTaskListEvent {
    public final ITaskAdapter adapter;

    /**
     * Constructor
     * @param adapter is the current adapter
     */
    public RequestTaskListEvent(ITaskAdapter adapter){
        this.adapter = adapter;
    }
}
