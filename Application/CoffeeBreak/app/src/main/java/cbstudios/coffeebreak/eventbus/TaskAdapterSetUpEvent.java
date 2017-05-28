package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.view.adapter.ITaskAdapter;


/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when task adapter will be set up </br >
 *          Used by: MainPresenter
 *          </p>
 */


public class TaskAdapterSetUpEvent {
    public final ITaskAdapter taskAdapter;

    /**
     *
     * @param taskAdapter is the current taskAdapter
     */
    public TaskAdapterSetUpEvent(ITaskAdapter taskAdapter){
        this.taskAdapter = taskAdapter;
    }
}
