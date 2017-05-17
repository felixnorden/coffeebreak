package cbstudios.coffeebreak.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.controller.ITaskDetailPresenter;
import cbstudios.coffeebreak.controller.PresenterFactory;

public class TaskDetailActivity extends AppCompatActivity implements ITaskDetailView {

    private ITaskDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        presenter = PresenterFactory.getInstance().createTaskDetailPresenter(this);
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }
}
