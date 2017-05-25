package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.model.Model;

/**
 * @author Felix
 * @version 2.0
 *          <p>Responsibility: Being the base class for all view-dependant presenters</br >
 *          Used by: {@link MainPresenter}, {@link TaskEditPresenter} and {@link AchievementPresenter}
 *          </p>
 */

class BasePresenter{
    protected Model model;

    // Identification Constants for readability.
    protected static final int ADVANCED_TASK = 0;
    protected static final int LIST_TASK = 1;
}
