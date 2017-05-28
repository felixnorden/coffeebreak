package cbstudios.coffeebreak.eventbus;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Event reflecting the activity's corresponding state-method</br >
 *          Uses: {@link Object}</br>
 *          Used by: {@link cbstudios.coffeebreak.view.activity.MainActivity},{@link cbstudios.coffeebreak.view.activity.TaskEditActivity},
 *          {@link cbstudios.coffeebreak.view.activity.AchievementActivity}
 *          </p>
 *
 */

public class OnStopEvent implements IActivityEvent {
    public final Object object;

    /**
     *
     * @param o is the current object
     */
    public OnStopEvent(Object o) {
        this.object = o;
    }
}
