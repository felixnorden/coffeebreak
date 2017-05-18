package cbstudios.coffeebreak.eventbus;

public class OnPauseEvent implements IEvent {
    private Object object;

    public OnPauseEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
