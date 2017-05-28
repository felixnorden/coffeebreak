package cbstudios.coffeebreak.eventbus;

import cbstudios.coffeebreak.controller.IPresenter;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Send event when presenter needs to be removed </br >
 *          Used by: MainPresenter, DelegatingPresenter
 *          </p>
 */

public class RemovePresenterEvent {
    public final IPresenter presenter;

    /**
     * Constructor
     * @param presenter is the presenter that will be removed
     */
    public RemovePresenterEvent(IPresenter presenter){
        this.presenter = presenter;
    }
}
