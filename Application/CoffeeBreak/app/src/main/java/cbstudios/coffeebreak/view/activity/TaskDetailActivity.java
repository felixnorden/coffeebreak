package cbstudios.coffeebreak.view.activity;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.controller.ITaskDetailPresenter;
import cbstudios.coffeebreak.controller.PresenterFactory;

public class TaskDetailActivity extends AppCompatActivity implements ITaskDetailView {

    private ITaskDetailPresenter presenter;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        textView = (TextView) findViewById(R.id.testText);

        presenter = PresenterFactory.getInstance().createTaskDetailPresenter(this);
        presenter.onCreate();
    }

    @Override
    protected void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @Override
    public void setNameText(String nameText) {
        textView.setText(nameText);
    }
}
