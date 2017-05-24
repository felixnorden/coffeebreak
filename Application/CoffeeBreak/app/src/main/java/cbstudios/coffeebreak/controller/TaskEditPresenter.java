package cbstudios.coffeebreak.controller;

import android.content.res.Resources;
import android.graphics.Color;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStartEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.TaskEditedEvent;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.activity.ITaskEditView;
import cbstudios.coffeebreak.view.activity.TaskEditActivity;

public class TaskEditPresenter extends BasePresenter implements ITaskEditPresenter {

    private ITaskEditView view;
    private final IAdvancedTask task;

    TaskEditPresenter(IAdvancedTask task) {
        this.task = task;
        EventBus.getDefault().register(this);
    }

    @Override
    public void injectModel(Model model) {
        this.model = model;
    }

    @Subscribe
    public void onTaskEdited(TaskEditedEvent event) {
        updateModel();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreate(OnCreateEvent event) {
        if (event.object instanceof ITaskEditView) {
            view = (ITaskEditView) event.object;
            setupView();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPause(OnPauseEvent event) {
        if (event.object instanceof ITaskEditView) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResume(OnResumeEvent event) {
        if (event.object instanceof ITaskEditView) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDestroy(OnDestroyEvent event) {
        if (event.object instanceof ITaskEditView) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStop(OnStopEvent event) {
        if (event.object instanceof ITaskEditView) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStart(OnStartEvent event) {
        if (event.object instanceof ITaskEditView)
            EventBus.getDefault().register(this);
    }

    /**
     * Updates view with current data in model.
     */
    private void setupView() {
        view.setTitle(task.getName());
        view.setNameText(task.getName());
        view.setNotification(task.getDate());
        view.setupCategories(task.getLabels(), model.getToDoDataModule().getLabelCategories());
        setupPriority();
        view.setNote(task.getNote());
    }

    private void setupPriority() {
        String priority = "";   //Default to NONE
        int color = 0;
        Resources r = view.getAppCompatActivity().getResources();

        switch (task.getPriority()) {
            case ONE:
                priority = r.getString(R.string.priority_high);
                color = Color.parseColor(Priority.ONE.getColor());
                break;
            case TWO:
                priority = r.getString(R.string.priority_medium);
                color = Color.parseColor(Priority.TWO.getColor());
                break;
            case THREE:
                priority = r.getString(R.string.priority_low);
                color = Color.parseColor(Priority.THREE.getColor());
                break;
        }

        view.setPriority(priority, color);
    }

    /**
     * Updates model with current data from view.
     * Calls setupView() to make sure view correctly reflects new model-state.
     */
    private void updateModel() {
        task.setName(view.getNameText());
        task.setDate(view.getNotification());
        task.setNote(view.getNote());
        updateLabels();
        updatePriority();

        setupView();
    }

    private void updatePriority() {
        final String current = view.getPriority();
        final String low = view.getAppCompatActivity().getResources().getString(R.string.priority_low);
        final String medium = view.getAppCompatActivity().getResources().getString(R.string.priority_medium);
        final String high = view.getAppCompatActivity().getResources().getString(R.string.priority_high);

        if (current.equals(high)) {
            task.setPriority(Priority.ONE);
        } else if (current.equals(medium)) {
            task.setPriority(Priority.TWO);
        } else if (current.equals(low)) {
            task.setPriority(Priority.THREE);
        } else {
            task.setPriority(Priority.NONE);
        }
    }

    /**
     * Updates the task with possible new information on
     */
    private void updateLabels() {
        String newName = view.getNewLabelText();

        if (newName == null || newName.equals(""))
            return;

        if (!task.hasILabelCategory(newName)) {
            task.addLabel(model.getToDoDataModule().getLabelCategory(newName));
            view.notifyCategoriesChanged();
        }
    }
}