package cbstudios.coffeebreak.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.controller.IPresenterFactory;
import cbstudios.coffeebreak.controller.PresenterFactory;
import cbstudios.coffeebreak.eventbus.CreateCategoryEvent;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStartEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.RequestPresenterEvent;
import cbstudios.coffeebreak.eventbus.RequestTaskCreationEvent;
import cbstudios.coffeebreak.eventbus.ShowAchievementEvent;
import cbstudios.coffeebreak.eventbus.ShowKeyboardEvent;
import cbstudios.coffeebreak.eventbus.SortListEvent;
import cbstudios.coffeebreak.eventbus.TimesAppStartedEvent;
import cbstudios.coffeebreak.eventbus.TimesNavOpenEvent;
import cbstudios.coffeebreak.eventbus.TimesTaskDeletedEvent;
import cbstudios.coffeebreak.eventbus.TimesUpdatedEvent;
import cbstudios.coffeebreak.eventbus.UpdateContextReferenceEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.util.StorageUtil;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;
import cbstudios.coffeebreak.view.adapter.LabelCategoryAdapter;
import cbstudios.coffeebreak.view.adapter.MergeAdapter;
import cbstudios.coffeebreak.view.adapter.SeparatorAdapter;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;
import cbstudios.coffeebreak.view.adapter.TimeCategoryAdapter;
import cbstudios.coffeebreak.view.fragment.DeleteFragment;
import cbstudios.coffeebreak.view.fragment.SortFragment;

/**
 * @author Felix, Elias
 * @version 1.1
 *          <p>Responsibility: Handles the logic of showing the main view</br >
 *          Uses: {@link IPresenterFactory}, {@link ITaskAdapter}, {@link LabelCategoryAdapter}, {@link TimeCategoryAdapter},
 *         {@link MergeAdapter}, </br>
 *         Events:
 *          <ul>
 *              <li>{@link RequestTaskCreationEvent}</li>
 *              <li>{@link OnStartEvent}</li>
 *              <li>{@link OnResumeEvent}</li>
 *              <li>{@link OnPauseEvent}</li>
 *              <li>{@link OnStopEvent}</li>
 *              <li>{@link OnCreateEvent}</li>
 *              <li>{@link RequestTaskCreationEvent}</li>
 *              <li>{@link RequestPresenterEvent}</li>
 *              <li>{@link ShowKeyboardEvent}</li>
 *              <li>{@link TimesNavOpenEvent}</li>
 *              <li>{@link TimesAppStartedEvent}</li>
 *              <li>{@link SortListEvent}</li>
 *              <li>{@link UpdateContextReferenceEvent}</li>
 *          </ul>
 *          Used by:
 *          {@link cbstudios.coffeebreak.controller.MainPresenter}
 *          </p>
 *
 */
public class MainActivity extends AppCompatActivity  implements IMainView {
    
    // Layout elements
    private ScrollView mDrawer;
    private DrawerLayout mDrawerLayout;
    private NonScrollListView mDrawerList;
    private Toolbar mToolbar;
    private FloatingActionButton fabAddBtn, fabAdvBtn, fabListBtn;
    private TextView txtAdvBtn, txtListBtn;
    private RecyclerView taskList;
    private SwipeRefreshLayout swipeRefreshLayout;

    

    // Animations
    private Animation FabOpen;
    private Animation FabClose;
    private Animation FabRClockwise;
    private Animation FabRAnticlockwise;
    private Animation TxtSlideIn;
    private Animation TxtSlideOut;
    
    // Control for animations
    private boolean isFabOpen = false;
    
    // Model data
    private final IPresenterFactory presenterFactory = PresenterFactory.getInstance();
    private List<ILabelCategory> labelCategories;
    private List<ITimeCategory> timeCategories;
    private ICategory currentCategory;

    private static boolean initialized = false;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = (RecyclerView) findViewById(R.id.taskList);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        taskList.setLayoutManager(new LinearLayoutManager(this));
        if(!initialized) {
            presenterFactory.initializeDelegatingPresenter(this);
            initialized = true;
        }
        else{
            EventBus.getDefault().post(new UpdateContextReferenceEvent(this));
        }
        attachPresenter();

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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
                EventBus.getDefault().post(new TimesUpdatedEvent());
            }
        });

        // TODO: 2017-05-27 hahhahahah remove
        // TODO: 2017-05-27 HAHAHA NEVAAHH!! 
        //enableEraseDataOnShutdown();

        EventBus.getDefault().post(new OnCreateEvent(this));
        EventBus.getDefault().post(new TimesAppStartedEvent());
    }

    //Long press on nav drawer header will reset data
    /*private void enableEraseDataOnShutdown() {
        TextView header = (TextView) findViewById(R.id.drawer_header);

        final Context context = this;

        header.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                StorageUtil.resetData(context, "Tasks");
                throw new RuntimeException("This crash is intended.");
            }
        });
    }*/

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart(){
        super.onStart();
        EventBus.getDefault().post(new OnStartEvent(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop(){
        EventBus.getDefault().post(new OnStopEvent(this));
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPause(){
        EventBus.getDefault().post(new OnPauseEvent(this));
        super.onPause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onResume(){
        super.onResume();
        EventBus.getDefault().post(new OnResumeEvent(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy(){
        EventBus.getDefault().post(new OnDestroyEvent(this));
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case(R.id.action_settings):
                showAchievementActivity();
                return true;
            case(R.id.action_sort):
                showSortingDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    /**
     * Sorts the list of tasks to the requested order
     * @param event
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onSortingOrderChange(SortListEvent event){
        switch (event.order){
            case SortListEvent.ORDERING_ALPHABETICAL:
                Toast.makeText(this, "Alphabetical order", Toast.LENGTH_SHORT).show();
                break;
            case SortListEvent.ORDERING_CHRONOLOGICAL:
                Toast.makeText(this, "Chronological order", Toast.LENGTH_SHORT).show();
                break;
            case SortListEvent.ORDERING_PRIORITY:
                Toast.makeText(this, "Priority order", Toast.LENGTH_SHORT).show();
                break;
            default: return;
        }
    }

    /**
     * Sets the title of the toolbar
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }


    /**
     * {@inheritDoc}
     */
    public ICategory getCurrentCategory(){
        return currentCategory;
    }

    public void updateNavDrawerList(){
        mDrawerList.invalidateViews();
    }

    /**
     * Assigns both timeCategories and labelCategories
     * @param labelCategories the models list of labelCategories
     * @param timeCategories the models list of timeCategories
     */
    @Override
    public void setCategories(List<ILabelCategory> labelCategories, List<ITimeCategory> timeCategories) {
        this.labelCategories = labelCategories;
        this.timeCategories = timeCategories;
    }

    /**
     * Does the necessary setup to show the navigation drawer
     */
    @Override
    public void setNavDrawer() {
        mDrawer = (ScrollView) findViewById(R.id.left_drawer);
        mDrawerList = (NonScrollListView) findViewById(R.id.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.setBackgroundColor(Color.WHITE);
        ImageButton mAddCategoryButton = (ImageButton) findViewById(R.id.add_category);


        final SeparatorAdapter timeSeparator = new SeparatorAdapter(this, SeparatorAdapter.TIME_CATEGORY);
        final SeparatorAdapter labelSeparator = new SeparatorAdapter(this, SeparatorAdapter.LABEL_CATEGORY);
        final LabelCategoryAdapter labelCategoryAdapter = new LabelCategoryAdapter(this, labelCategories);
        final TimeCategoryAdapter timeCategoryAdapter = new TimeCategoryAdapter(this, timeCategories);
        final MergeAdapter mergeAdapter = new MergeAdapter();

        mergeAdapter.addAdapter(timeSeparator);
        mergeAdapter.addAdapter(timeCategoryAdapter);
        mergeAdapter.addAdapter(labelSeparator);
        mergeAdapter.addAdapter(labelCategoryAdapter);
        mDrawerList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 6) {
                    int labelIndex = mergeAdapter.getCount() - labelCategoryAdapter.getCount();
                    showCategoryDeleteDialog(position - labelIndex);
                }
                return true;
            }
        });


        mDrawerList.setAdapter(mergeAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mAddCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new CreateCategoryEvent());
                ((MergeAdapter) mDrawerList.getAdapter()).notifyDataSetChanged();
                displayKeyboard();

            }
        });


    }

    /**
     * Does the necessary setup to show the ToolBar
     */
    @Override
    public void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(currentCategory.getName());

        setDrawerButton();
    }


    /**
     * {@inheritDoc}
     */
    public void setCurrentCategory(ICategory currentCategory){
        this.currentCategory = currentCategory;

    }

    /**
     * Assigns the adapter of the taskList
     * @param adapter the adapter to be assigned
     */
    @Override
    public void setTaskAdapter(ITaskAdapter adapter) {
        taskList.setAdapter((TaskAdapter) adapter);
    }


    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    /**
     * handles showing/hiding the keyboard
     * @param event contains the view that has the focus and a boolean to
     *              determine whether to show or hide keyboard
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayKeyboard(ShowKeyboardEvent event){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(event.showKeyboard){
            imm.showSoftInput(event.view, InputMethodManager.SHOW_IMPLICIT);
        }
        else{
            imm.hideSoftInputFromWindow(event.view.getWindowToken(), 0);
        }
    }

    /**
     * Shows the Sorting dialog to allow the user to sort the tasks
     */
    private void showSortingDialog() {
        DialogFragment dialog = new SortFragment();
        dialog.show(getSupportFragmentManager(), "sorting");
    }

    private void showCategoryDeleteDialog(int position) {
        DeleteFragment dialog = new DeleteFragment();
        dialog.setPosition(position);
        dialog.show(getSupportFragmentManager(), "category");
    }

    /**
     * Request a new presenter to be attached
     */
    private void attachPresenter(){
            EventBus.getDefault().post(new RequestPresenterEvent(this));
    }


    private void toggleFabState() {
        isFabOpen = !isFabOpen;
    }

    /**
     * Refresh the items that the taskAdapter holds
     */
    private void refreshItems() {
        ITaskAdapter adapter = (ITaskAdapter) taskList.getAdapter();
        adapter.refreshItems(currentCategory);

        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Initiates the animation of the FAB-button
     */
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

    /**
     * Setups the animations
     */
    private void setAnimations() {
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRAnticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        TxtSlideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        TxtSlideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
    }

    /**
     * Adds a new AdvancedTasks and updates the the items that the TaskAdapter holds
     */
    private void addAdvTask() {
        EventBus.getDefault().post(new RequestTaskCreationEvent(RequestTaskCreationEvent.ADVANCED_TASK));
        updateAdapter();
    }

    /**
     * Adds a new ListTask and updates the the items that the TaskAdapter holds
     */
    private void addListTask() {
        EventBus.getDefault().post(new RequestTaskCreationEvent(RequestTaskCreationEvent.LIST_TASK));
        updateAdapter();
    }

    /**
     * Shows the keyboard and makes it focus on the RecyclerView holding the tasks
     */
    private void displayKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(taskList, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Updates the tasks that the TaskAdapter
     */
    private void updateAdapter(){
        TaskAdapter taskAdapter = (TaskAdapter) taskList.getAdapter();
        taskAdapter.filterTasks(currentCategory);
    }

    /**
     * Handles clicks on categories in the Navigation Drawer
     */
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

        if(position == 0 || position == 6) {
            return;
        }
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        ICategory category = (ICategory) mDrawerList.getAdapter().getItem(position);
        setTitle(category.getName());
        currentCategory = category;

        // Set adapter
        TaskAdapter taskAdapter = (TaskAdapter) taskList.getAdapter();
        taskAdapter.filterTasks(currentCategory);

        // Close drawer
        mDrawerLayout.closeDrawer(mDrawer);
    }


    /**
     * Setups the Navigation drawer button
     */
    private void setDrawerButton() {
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    private void showAchievementActivity(){
        EventBus.getDefault().post(new ShowAchievementEvent());
    }
}