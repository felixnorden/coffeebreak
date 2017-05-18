package cbstudios.coffeebreak.eventbus;

public class OnStopEvent implements IEvent {
    public final Object object;

    public OnStopEvent(Object o) {
        this.object = o;
    }
}
