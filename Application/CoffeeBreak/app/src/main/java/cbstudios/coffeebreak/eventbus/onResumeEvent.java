package cbstudios.coffeebreak.eventbus;

public class OnResumeEvent implements IEvent {
    private Object object;

    public OnResumeEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
