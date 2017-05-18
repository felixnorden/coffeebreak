package cbstudios.coffeebreak.view.activity;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.controller.IMainPresenter;
import cbstudios.coffeebreak.controller.IPresenterFactory;
import cbstudios.coffeebreak.controller.PresenterFactory;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStartEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.QueryRegistrationEvent;
import cbstudios.coffeebreak.eventbus.ShowKeyboardEvent;
import cbstudios.coffeebreak.eventbus.StatisticEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;
import cbstudios.coffeebreak.view.adapter.LabelCategoryAdapter;
import cbstudios.coffeebreak.view.adapter.MergeAdapter;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;
import cbstudios.coffeebreak.view.adapter.TimeCategoryAdapter;

public class MainActivity extends AppCompatActivity  implements IMainView {
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private RelativeLayout mDrawer;
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

    private IMainPresenter mainPresenter;
    private final IPresenterFactory presenterFactory = PresenterFactory.getInstance();
    private List<ILabelCategory> labelCategories;
    private List<ITimeCategory> timeCategories;
    private RecyclerView taskList;
    private ICategory currentCategory = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = (RecyclerView) findViewById(R.id.taskList);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter = presenterFactory.createMainPresenter(this);


        // Set up Buttons for adding tasks
        fabAddBtn = (FloatingActionButton) findViewById(R.id.fab_add_task);
        fabAdvBtn = (FloatingActionButton) findViewById(R.id.fab_advanced_task);
        fabListBtn = (FloatingActionButton) findViewById(R.id.fab_list_task);

        txtAdvBtn = (TextView) findViewById(R.id.advanced_text);
        txtListBtn = (TextView) findViewById(R.id.list_text);

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
                displayKeyboard();
            }
        });
        fabListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListTask();
                startFabAnimation();
                displayKeyboard();
            }
        });

        EventBus.getDefault().post(new OnCreateEvent(this));
        EventBus.getDefault().post(new StatisticEvent("TimesAppStarted"));

    }

    @Override
    protected void onStart(){
        super.onStart();
        EventBus.getDefault().post(new OnStartEvent(this));
    }

    @Override
    protected void onStop(){
        EventBus.getDefault().post(new OnStopEvent(this));
        super.onStop();
    }
    @Override
    protected void onPause(){
        EventBus.getDefault().post(new OnPauseEvent(this));
        //mainPresenter.onPause(new OnPauseEvent(this));
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        EventBus.getDefault().post(new OnResumeEvent(this));
    }

    @Override
    protected void onDestroy(){
        EventBus.getDefault().post(new OnDestroyEvent(this));
        super.onDestroy();
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


    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    public ICategory getCurrentCategory(){
        return currentCategory;
    }

    @Override
    public void setCategories(List<ILabelCategory> labelCategories, List<ITimeCategory> timeCategories) {
        this.labelCategories = labelCategories;
        this.timeCategories = timeCategories;
    }

    @Override
    public void setNavDrawer() {
        mDrawer = (RelativeLayout) findViewById(R.id.left_drawer);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.setBackgroundColor(Color.WHITE);

        final LabelCategoryAdapter labelCategoryAdapter = new LabelCategoryAdapter(this, labelCategories, mainPresenter);
        final TimeCategoryAdapter timeCategoryAdapter = new TimeCategoryAdapter(this, timeCategories, mainPresenter);
        final MergeAdapter mergeAdapter = new MergeAdapter();

        mergeAdapter.addAdapter(timeCategoryAdapter);
        mergeAdapter.addAdapter(labelCategoryAdapter);

        mDrawerList.setAdapter(mergeAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    @Override
    public void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("All");

        setDrawerButton();

    }

    @Override
    public void setTaskAdapter(ITaskAdapter adapter) {
        taskList.setAdapter((TaskAdapter) adapter);
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayKeyboard(ShowKeyboardEvent event){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(event.showKeyboard){
            imm.hideSoftInputFromWindow(event.view.getWindowToken(), 0);
        }
        else{
            imm.showSoftInput(event.view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void toggleFabState() {
        isFabOpen = !isFabOpen;
    }

    private void startFabAnimation() {
        if (isFabOpen) {
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
        } else {
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
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRAnticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        TxtSlideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        TxtSlideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
    }

    private void addAdvTask() {
        mainPresenter.createAdvancedTask();
        updateAdapter();
    }


    private void addListTask() {
        mainPresenter.createListTask();
        updateAdapter();
    }

    private void displayKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(taskList, InputMethodManager.SHOW_IMPLICIT);
    }

    private void updateAdapter(){
        TaskAdapter taskAdapter = (TaskAdapter) taskList.getAdapter();
        taskAdapter.filterTasks();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);


        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        ICategory category = (ICategory) mDrawerList.getAdapter().getItem(position);
        setTitle(category.getName());
        currentCategory = category;

        // Set adapter
        //TODO UGLY AF
        TaskAdapter taskAdapter = (TaskAdapter) taskList.getAdapter();
        taskAdapter.updateTasks();
        taskAdapter.filterTasks(currentCategory);

        // Close drawer
        mDrawerLayout.closeDrawer(mDrawer);
    }


    private void setDrawerButton() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset != 0){
                    EventBus.getDefault().post(new StatisticEvent("TimesNavOpen"));
                }
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        mActionBarDrawerToggle.syncState();

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }
}