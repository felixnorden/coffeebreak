package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.view.adapter.TaskAdapter;

/**
 * Created by Lenovo on 2017-05-18.
 */

public class TaskAdapterSetUpEvent {
    public final TaskAdapter taskAdapter;

    public TaskAdapterSetUpEvent(TaskAdapter taskAdapter){
        this.taskAdapter = taskAdapter;
    }
}
