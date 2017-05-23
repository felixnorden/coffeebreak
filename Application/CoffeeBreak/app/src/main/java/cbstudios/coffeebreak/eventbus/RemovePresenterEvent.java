package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.controller.IPresenter;

/**
 * Created by Felix on 2017-05-23.
 */

public class RemovePresenterEvent {
    public final IPresenter presenter;

    public RemovePresenterEvent(IPresenter presenter){
        this.presenter = presenter;
    }
}
