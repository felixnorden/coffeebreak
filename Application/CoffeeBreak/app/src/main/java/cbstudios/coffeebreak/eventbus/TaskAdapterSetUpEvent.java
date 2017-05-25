package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.view.adapter.ITaskAdapter;


/**
 * Created by Lenovo on 2017-05-18.
 */

public class TaskAdapterSetUpEvent {
    public final ITaskAdapter taskAdapter;

    public TaskAdapterSetUpEvent(ITaskAdapter taskAdapter){
        this.taskAdapter = taskAdapter;
    }
}
