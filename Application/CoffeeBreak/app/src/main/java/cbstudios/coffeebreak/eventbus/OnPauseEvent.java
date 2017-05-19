package cbstudios.coffeebreak.eventbus;

public class OnPauseEvent implements IEvent {
    public final Object object;

    public OnPauseEvent(Object o) {
        this.object = o;
    }

}
