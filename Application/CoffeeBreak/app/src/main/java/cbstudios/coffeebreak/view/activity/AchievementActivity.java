package cbstudios.coffeebreak.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.CreateCategoryEvent;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.RequestPresenterEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.adapter.AchievementAdapter;
import cbstudios.coffeebreak.view.adapter.IAchievementAdapter;
import cbstudios.coffeebreak.view.adapter.LabelCategoryAdapter;
import cbstudios.coffeebreak.view.adapter.MergeAdapter;
import cbstudios.coffeebreak.view.adapter.TimeCategoryAdapter;

/**
 * Created by johan on 5/25/2017.
 */

public class AchievementActivity extends AppCompatActivity implements IAchievementView {

    private RecyclerView achievementGrid;
    private List<ILabelCategory> labelCategories;
    private List<ITimeCategory> timeCategories;
    private ScrollView mDrawer;
    private NonScrollListView mDrawerList;
    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.achievement_activity_layout);

        achievementGrid = (RecyclerView) findViewById(R.id.achievementGrid);

        achievementGrid.setLayoutManager(new GridLayoutManager(this, 4));
        EventBus.getDefault().post(new RequestPresenterEvent(this));
        EventBus.getDefault().post(new OnCreateEvent(this));

    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @Override
    public void setCategories(List<ILabelCategory> labelCategories, List<ITimeCategory> timeCategories) {
        this.labelCategories = labelCategories;
        this.timeCategories = timeCategories;
    }

    @Override
    public void setAchievementAdapter(IAchievementAdapter adapter) {
        achievementGrid.setAdapter((AchievementAdapter)adapter);
    }

    @Override
    public void setNavDrawer() {
        mDrawer = (ScrollView) findViewById(R.id.left_drawer);
        mDrawerList = (NonScrollListView) findViewById(R.id.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer.setBackgroundColor(Color.WHITE);
        ImageButton mAddCategoryButton = (ImageButton) findViewById(R.id.add_category);


        final LabelCategoryAdapter labelCategoryAdapter = new LabelCategoryAdapter(this, labelCategories);
        final TimeCategoryAdapter timeCategoryAdapter = new TimeCategoryAdapter(this, timeCategories);
        final MergeAdapter mergeAdapter = new MergeAdapter();


        mergeAdapter.addAdapter(timeCategoryAdapter);
        mergeAdapter.addAdapter(labelCategoryAdapter);

        mDrawerList.setAdapter(mergeAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mAddCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new CreateCategoryEvent());
                ((MergeAdapter) mDrawerList.getAdapter()).notifyDataSetChanged();
            }
        });



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



        // Close drawer
        mDrawerLayout.closeDrawer(mDrawer);
    }

    @Override
    public void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Achievements");

        setDrawerButton();
    }

    private void setDrawerButton() {
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }
}
