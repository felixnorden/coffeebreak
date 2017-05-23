package cbstudios.coffeebreak.eventbus;

import android.service.voice.VoiceInteractionSession;

import cbstudios.coffeebreak.view.adapter.ITaskAdapter;

/**
 * Created by Felix on 2017-05-23.
 */

public class RequestTaskListEvent {
    public final ITaskAdapter adapter;

    public RequestTaskListEvent(ITaskAdapter adapter){
        this.adapter = adapter;
    }
}
