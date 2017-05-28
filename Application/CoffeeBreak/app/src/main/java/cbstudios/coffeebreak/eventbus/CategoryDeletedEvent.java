package cbstudios.coffeebreak.eventbus;

/**
 * Created by elias on 26/05/2017.
 */

public class CategoryDeletedEvent implements IEvent {
    public int which;
    public int position;

    public CategoryDeletedEvent(int which, int position){
        this.which = which;
        this.position = position;

    }
}
