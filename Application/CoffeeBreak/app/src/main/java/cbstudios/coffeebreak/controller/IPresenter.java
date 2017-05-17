package cbstudios.coffeebreak.controller;

public interface IPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
    void onStop();
}
