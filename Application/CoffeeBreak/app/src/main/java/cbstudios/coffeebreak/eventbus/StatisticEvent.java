package cbstudios.coffeebreak.eventbus;

/**
 * Created by johan on 5/8/2017.
 */

public class StatisticEvent {
    private final String message;

    public StatisticEvent(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
