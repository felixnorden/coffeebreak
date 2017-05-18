package cbstudios.coffeebreak.eventbus;

public class onPauseEvent implements IEvent {
    private Object object;

    public onPauseEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
