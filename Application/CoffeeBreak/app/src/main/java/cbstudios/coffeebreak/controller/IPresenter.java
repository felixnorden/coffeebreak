package cbstudios.coffeebreak.controller;

import cbstudios.coffeebreak.eventbus.*;

public interface IPresenter {
    void onCreate(onCreateEvent event);
    void onPause(onPauseEvent event);
    void onResume(onResumeEvent event);
    void onDestroy(onDestroyEvent event);
    void onStop(onStopEvent event);
}
