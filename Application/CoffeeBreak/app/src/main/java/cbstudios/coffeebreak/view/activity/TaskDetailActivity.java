package cbstudios.coffeebreak.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cbstudios.coffeebreak.R;

public class TaskDetailActivity extends AppCompatActivity implements ITaskDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }
}
