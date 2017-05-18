package cbstudios.coffeebreak.eventbus;

public class onResumeEvent implements IEvent {
    private Object object;

    public onResumeEvent(Object o) {
        this.object = o;
    }

    public Object getObject() {
        return object;
    }
}
