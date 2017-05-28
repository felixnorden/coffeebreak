package cbstudios.coffeebreak.eventbus;

/**
 * @author Elias
 * @version 1.0
 *          <p>Responsibility: Send event when a category needs to be removed from a task </br >
 *          Used by: MainPresenter, MainActivity
 *          </p>
 */

public class RemoveCategoryFromTaskEvent implements IEvent {
    public String name;

    /**
     * Constructor
     * @param name is the name of the category
     */
    public RemoveCategoryFromTaskEvent(String name){
        this.name = name;
    }
}
