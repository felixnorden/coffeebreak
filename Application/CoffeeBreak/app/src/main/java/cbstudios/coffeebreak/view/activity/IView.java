package cbstudios.coffeebreak.view.activity;

import android.support.v7.app.AppCompatActivity;

import cbstudios.coffeebreak.controller.IPresenter;

/**
 * @author Zack
 * @version 1.0
 *          <p>Responsibility: Abstracting interface for all view (Activites)</br >
 *          Used by: IMainView, ITaskEditView </br>
 *          Uses: AppCompatActivity.
 *          </p>
 */

public interface IView {
    /**
     * Pretty much casts the view into a AppCompatView,
     *
     * @return Returns the AppCompatActivity of the view.
     */
    AppCompatActivity getAppCompatActivity();
}
