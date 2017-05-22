package cbstudios.coffeebreak.eventbus;


import cbstudios.coffeebreak.view.activity.IView;

/**
 * Created by Felix on 2017-05-22.
 */

public class RequestPresenterEvent {
    public final IView view;

    public RequestPresenterEvent(IView view){
        this.view = view;
    }
}
