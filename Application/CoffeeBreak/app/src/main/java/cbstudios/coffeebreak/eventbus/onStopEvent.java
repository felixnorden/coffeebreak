package cbstudios.coffeebreak.eventbus;

public class OnStopEvent implements IEvent {
    private Object object;

    public OnStopEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
