package cbstudios.coffeebreak.eventbus;

public class OnCreateEvent implements IEvent {
    public final Object object;

    public OnCreateEvent(Object o) {
        this.object = o;
    }

}
