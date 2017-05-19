package cbstudios.coffeebreak.eventbus;

public class OnDestroyEvent implements IEvent {
    public final Object object;

    public OnDestroyEvent(Object o) {
        this.object = o;
    }

}
