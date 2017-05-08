package cbstudios.coffeebreak.eventBus;

/**
 * Created by johan on 5/8/2017.
 */

public class statisticEvent {
    private final String message;

    public statisticEvent(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
