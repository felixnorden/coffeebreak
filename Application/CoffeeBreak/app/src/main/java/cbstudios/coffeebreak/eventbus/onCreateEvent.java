package cbstudios.coffeebreak.eventbus;

public class onCreateEvent implements IEvent {
    private Object object;

    public onCreateEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
