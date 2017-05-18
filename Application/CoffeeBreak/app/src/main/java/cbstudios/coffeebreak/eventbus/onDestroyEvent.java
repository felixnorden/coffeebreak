package cbstudios.coffeebreak.eventbus;

public class OnDestroyEvent implements IEvent {
    private Object object;

    public OnDestroyEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
