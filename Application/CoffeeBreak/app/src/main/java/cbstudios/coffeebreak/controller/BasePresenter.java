package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.model.Model;

/**
 * @author Felix
 * @version 1.0
 * Responsibility: Hold the only reference to the instatiated Model for each extending subclass Presenter.
 */

class BasePresenter{
    protected Model model;
    protected static final int ADVANCED_TASK = 0;
    protected static final int LIST_TASK = 1;
}
