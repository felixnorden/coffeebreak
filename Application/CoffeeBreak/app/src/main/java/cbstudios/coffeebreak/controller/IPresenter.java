package cbstudios.coffeebreak.controller;

/**
 * Created by Zack on 2017-05-17.
 */

public interface IPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
