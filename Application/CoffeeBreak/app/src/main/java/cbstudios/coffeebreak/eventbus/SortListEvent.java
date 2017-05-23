package cbstudios.coffeebreak.eventbus;

/**
 * Created by Felix on 2017-05-23.
 */

public class SortListEvent {
    public static final int ORDERING_ALPHABETICAL = 0;
    public static final int ORDERING_CHRONOLOGICAL = 1;
    public static final int ORDERING_PRIORITY = 2;

    public final int order;

    public SortListEvent(int order){
        this.order = order;
    }
}
