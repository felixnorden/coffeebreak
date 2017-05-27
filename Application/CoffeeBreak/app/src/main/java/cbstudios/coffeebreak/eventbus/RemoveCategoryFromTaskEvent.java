package cbstudios.coffeebreak.eventbus;

/**
 * Created by elias on 27/05/2017.
 */

public class RemoveCategoryFromTaskEvent implements IEvent {
    public String name;

    public RemoveCategoryFromTaskEvent(String name){
        this.name = name;
    }
}
