package cbstudios.coffeebreak.eventbus;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when the list with tasks will be sorted </br >
 *          Used by: MainPresenter, MainActivity, DeleteFragment, SortFragment
 *          </p>
 */


public class SortListEvent {
    public static final int ORDERING_ALPHABETICAL = 0;
    public static final int ORDERING_CHRONOLOGICAL = 1;
    public static final int ORDERING_PRIORITY = 2;

    public final int order;

    /**
     * Constructor
     * @param order is a representation of which order the list will be sorted in
     */
    public SortListEvent(int order){
        this.order = order;
    }
}
