package com.lubarov.daniel.mixologist.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.fragments.ViewRecipeFragment;
import com.lubarov.daniel.mixologist.fragments.ViewSearchResultsFragment;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;
import com.lubarov.daniel.mixologist.reviewnagger.NagDecider;
import com.lubarov.daniel.mixologist.reviewnagger.ReviewDialogFragment;

import java.util.concurrent.atomic.AtomicBoolean;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private ThePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.main_activity_layout);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ThePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        final AtomicBoolean created = new AtomicBoolean();
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                if (created.get()) {
                    ContainerFragment container = (ContainerFragment) pagerAdapter.getRegisteredFragment(tab.getPosition());
                    if (container != null)
                        container.showOrHideMenu(true);
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                if (created.get()) {
                    ContainerFragment container = (ContainerFragment) pagerAdapter.getRegisteredFragment(tab.getPosition());
                    if (container != null)
                        container.showOrHideMenu(false);
                }
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        };

        actionBar.addTab(actionBar.newTab().setText("Inventory").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Recipes").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Favorites").setTabListener(tabListener));

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        created.set(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NagDecider.shouldNag(this))
            new ReviewDialogFragment().show(getSupportFragmentManager(), "rate");
    }

    @Override
    public void onBackPressed() {
        if (!getFocusedContainer().popFragment())
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global_actions, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int i) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int i) {
                Recipe recipe = RecipeData.ALL_RECIPES.get(getSuggestion(i));
                getFocusedContainer().pushFragment(ViewRecipeFragment.create(recipe));
                MenuItemCompat.collapseActionView(menuItem);
                return true;
            }

            int getSuggestion(int position) {
                Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
                return cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getFocusedContainer().pushFragment(ViewSearchResultsFragment.create(query));
                MenuItemCompat.collapseActionView(menuItem);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_preferences:
                startActivity(new Intent(this, PreferencesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ContainerFragment getFocusedContainer() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        return (ContainerFragment) pagerAdapter.getRegisteredFragment(viewPager.getCurrentItem());
    }
}
