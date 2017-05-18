package cbstudios.coffeebreak.eventbus;

public class onStopEvent implements IEvent {
    private Object object;

    public onStopEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
