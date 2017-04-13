package cbstudios.coffeebreak.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.adapter.TaskAdapter;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private final Model model = new Model();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<IAdvancedTask> tasks = model.getToDoDataModule().getTasksDummy();
        TaskAdapter taskAdapter = new TaskAdapter(this, tasks);
        ListView taskList = (ListView) findViewById(R.id.taskList);
        taskList.setAdapter(taskAdapter);

        //TODO Set up on click functionality;


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
