package cbstudios.coffeebreak.eventbus;

/**
 * @author Felix, Elias
 * @version 1.0
 *          <p>Responsibility: Notify listeners of a task removal request</br >
 *          Used by: {@link cbstudios.coffeebreak.view.adapter.TaskAdapter}
 *          </p>
 *
 */

public class TaskDeletedEvent {
    public final int which;
    public final int position;

    public TaskDeletedEvent(int which, int position) {
        this.which = which;
        this.position = position;
    }
}
