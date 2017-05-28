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
import cbstudios.coffeebreak.eventbus.TimesUpdatedEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.NonScrollListView;
import cbstudios.coffeebreak.view.adapter.AchievementAdapter;
import cbstudios.coffeebreak.view.adapter.IAchievementAdapter;
import cbstudios.coffeebreak.view.adapter.LabelCategoryAdapter;
import cbstudios.coffeebreak.view.adapter.MergeAdapter;
import cbstudios.coffeebreak.view.adapter.TimeCategoryAdapter;


public class AchievementActivity extends AppCompatActivity implements IAchievementView {

    private RecyclerView achievementGrid;
    private SwipeRefreshLayout refreshLayout;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.achievement_activity_layout);

        achievementGrid = (RecyclerView) findViewById(R.id.achievementGrid);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        achievementGrid.setLayoutManager(new GridLayoutManager(this, 4));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
        EventBus.getDefault().post(new RequestPresenterEvent(this));
        EventBus.getDefault().post(new OnCreateEvent(this));

    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }


    @Override
    public void setAchievementAdapter(AchievementAdapter adapter) {
        achievementGrid.setAdapter(adapter);
    }

    public void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Achievements");

    }

    /**
     * Refresh the items that the taskAdapter holds
     */
    private void refreshItems() {
        IAchievementAdapter adapter = (IAchievementAdapter) achievementGrid.getAdapter();
        adapter.refreshItems();
        EventBus.getDefault().post(new TimesUpdatedEvent());

        // Stop refresh animation
        refreshLayout.setRefreshing(false);
    }
}
