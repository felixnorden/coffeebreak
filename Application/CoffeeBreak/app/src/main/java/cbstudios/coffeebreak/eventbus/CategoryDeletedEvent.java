package cbstudios.coffeebreak.eventbus;

/**
 * @author Elias
 * @version 1.0
 *          <p>Responsibility: Send event when category is deleted</br >
 *          Used by: MainPresenter, MainActivity
 *          </p>
 */

public class CategoryDeletedEvent implements IEvent {
    public int which;
    public int position;

    /**
     *
     * @param which is which category that will be removed
     * @param position is the current position
     */
    public CategoryDeletedEvent(int which, int position){
        this.which = which;
        this.position = position;

    }
}
