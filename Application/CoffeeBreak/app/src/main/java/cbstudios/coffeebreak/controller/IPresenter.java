package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.eventbus.*;
import cbstudios.coffeebreak.model.Model;
/**
 * @author Felix, Zack
 * @version 1.1
 *          <p>Responsibility: Interface for all presenter classes to implement for handling all
 *          activity state methods in {@link android.app.Activity}</br>
 *          Uses Events:
 *          <ul>
 *              <li>{@link OnCreateEvent}</li>
 *              <li>{@link OnStartEvent}</li>
 *              <li>{@link OnResumeEvent}</li>
 *              <li>{@link OnPauseEvent}</li>
 *              <li>{@link OnStopEvent}</li>
 *              <li>{@link OnDestroyEvent}</li>
*              </ul>
*              </br>
 *          Used by: {@link IMainPresenter}, {@link ITaskEditPresenter}, {@link IAchievementPresenter} and {@link IPresenterFactory}
 *          </p>
 *
 */


public interface IPresenter {
    /**
     * Handles a view's external during its onCreate() call
     * @param event contains the necessary context
     */
    void onCreate(OnCreateEvent event);

    /**
     * Handles a view's external during its onPause() call
     * @param event contains the necessary context
     */
    void onPause(OnPauseEvent event);

    /**
     * Handles a view's external during its onResume() call
     * @param event contains the necessary context
     */
    void onResume(OnResumeEvent event);

    /**
     * Handles a view's external during its onDestroy() call
     * @param event contains the necessary context
     */
    void onDestroy(OnDestroyEvent event);

    /**
     * Handles a view's external during its onStop() call
     * @param event contains the necessary context
     */
    void onStop(OnStopEvent event);

    /**
     * Handles a view's external during its onStart() call
     * @param event contains the necessary context
     */
    void onStart(OnStartEvent event);
    void injectModel(Model model);
}
