package cbstudios.coffeebreak.eventbus;

public class onDestroyEvent implements IEvent {
    private Object object;

    public onDestroyEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
