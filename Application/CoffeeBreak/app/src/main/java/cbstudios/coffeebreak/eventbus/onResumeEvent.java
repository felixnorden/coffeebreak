package cbstudios.coffeebreak.eventbus;

public class OnResumeEvent implements IEvent {
    public final Object object;

    public OnResumeEvent(Object o) {
        this.object = o;
    }
}
