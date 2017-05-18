package cbstudios.coffeebreak.eventbus;

public class OnCreateEvent implements IEvent {
    private Object object;

    public OnCreateEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
