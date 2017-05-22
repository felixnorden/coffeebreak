package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.eventbus.*;
import cbstudios.coffeebreak.model.Model;

public interface IPresenter {
    void onCreate(OnCreateEvent event);
    void onPause(OnPauseEvent event);
    void onResume(OnResumeEvent event);
    void onDestroy(OnDestroyEvent event);
    void onStop(OnStopEvent event);
    void onStart(OnStartEvent event);
    void injectModel(Model model);
}
