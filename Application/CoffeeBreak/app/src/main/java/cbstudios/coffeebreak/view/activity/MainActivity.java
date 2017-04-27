package cbstudios.coffeebreak.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.view.adapter.LabelCategoryAdapter;
import cbstudios.coffeebreak.view.adapter.MergeAdapter;
import cbstudios.coffeebreak.view.adapter.TasksAdapter;
import cbstudios.coffeebreak.view.adapter.TimeCategoryAdapter;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Toolbar mToolbar;
    private FloatingActionButton fabAddBtn, fabAdvBtn, fabListBtn;
    private TextView txtAdvBtn, txtListBtn;
    //private LinearLayout fabAdvGroup, fabListGroup;

    private boolean isFabOpen = false;

    private Animation FabOpen;
    private Animation FabClose;
    private Animation FabRClockwise;
    private Animation FabRAnticlockwise;
    private Animation TxtSlideIn;
    private Animation TxtSlideOut;


    private final Model model = new Model();
    private List<IAdvancedTask> tasks = model.getToDoDataModule().getTasks();
    private List<ILabelCategory> labelCategories = model.getToDoDataModule().getLabelCategories();
    private List<ITimeCategory> timeCategories = model.getToDoDataModule().getTimeCategories();
    private RecyclerView taskList;
    private ICategory currentCategory= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set up Buttons for adding tasks
        fabAddBtn = (FloatingActionButton) findViewById(R.id.fab_add_task);
        fabAdvBtn = (FloatingActionButton) findViewById(R.id.fab_advanced_task);
        fabListBtn = (FloatingActionButton) findViewById(R.id.fab_list_task);

        txtAdvBtn = (TextView) findViewById(R.id.advanced_text);
        txtListBtn = (TextView) findViewById(R.id.list_text);

        //TODO
        // Set up navDrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        final LabelCategoryAdapter labelCategoryAdapter = new LabelCategoryAdapter(this, labelCategories);
        final TimeCategoryAdapter timeCategoryAdapter = new TimeCategoryAdapter(this, timeCategories);
        final MergeAdapter mergeAdapter = new MergeAdapter();
        mergeAdapter.addAdapter(timeCategoryAdapter);
        mergeAdapter.addAdapter(labelCategoryAdapter);
        mDrawerList.setAdapter(mergeAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



        // Set up RecyclerView for tasks and render each item.
        final TasksAdapter taskAdapter = new TasksAdapter(this, tasks, model);
        taskList = (RecyclerView) findViewById(R.id.taskList);
        taskList.setAdapter(taskAdapter);
        taskList.setLayoutManager(new LinearLayoutManager(this));

        //TODO Set up on click functionality
        //TODO Bind FAB for creation
        // Generate animations and bind listeners
        setAnimations();
        fabAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFabAnimation();
            }
        });
        fabAdvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAdvTask();
                startFabAnimation();

                // Notify adapter to update, with alias to data list.
                //taskList.getAdapter().notifyItemInserted(taskList.getAdapter().getItemCount());
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(taskList, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // Load in and set up Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("All");

        // Set up drawer button in toolbar
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
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

    private void toggleFabState(){
        isFabOpen = !isFabOpen;
    }

    private void startFabAnimation(){
        if(isFabOpen){
            fabAdvBtn.startAnimation(FabClose);
            fabListBtn.startAnimation(FabClose);
            fabAddBtn.startAnimation(FabRAnticlockwise);
            txtAdvBtn.startAnimation(TxtSlideOut);
            txtListBtn.startAnimation(TxtSlideOut);
            fabAdvBtn.setClickable(false);
            fabListBtn.setClickable(false);
            txtAdvBtn.setVisibility(View.INVISIBLE);
            txtListBtn.setVisibility(View.INVISIBLE);

            toggleFabState();
        }
        else{
            fabAdvBtn.startAnimation(FabOpen);
            fabListBtn.startAnimation(FabOpen);
            fabAddBtn.startAnimation(FabRClockwise);
            txtAdvBtn.startAnimation(TxtSlideIn);
            txtListBtn.startAnimation(TxtSlideIn);
            fabAdvBtn.setClickable(true);
            fabListBtn.setClickable(true);
            txtAdvBtn.setVisibility(View.VISIBLE);
            txtListBtn.setVisibility(View.VISIBLE);

            toggleFabState();
        }
    }

    private void setAnimations() {
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRAnticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        TxtSlideIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        TxtSlideOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
    }

    private void addAdvTask() {
        model.getToDoDataModule().createTask();
        if(currentCategory == null){
            taskList.setAdapter(new TasksAdapter(this, model.getToDoDataModule().getTasks(), model));
        }
        else{
            taskList.setAdapter(new TasksAdapter(this, currentCategory.getValidTasks(model.getToDoDataModule().getTasks()), model));
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);


        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {



        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        ICategory category = (ICategory) mDrawerList.getAdapter().getItem(position);
        setTitle(category.getName());
        currentCategory = category;

        taskList.setAdapter(new TasksAdapter(this, currentCategory.getValidTasks(model.getToDoDataModule().getTasks()), model));




        mDrawerLayout.closeDrawer(mDrawerList);


    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }
    }
