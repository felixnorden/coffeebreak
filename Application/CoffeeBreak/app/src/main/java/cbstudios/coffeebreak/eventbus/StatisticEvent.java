package cbstudios.coffeebreak.eventbus;

public class StatisticEvent implements IEvent {
    private final String message;

    public StatisticEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
