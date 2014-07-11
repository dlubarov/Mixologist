package com.lubarov.daniel.mixologist.activity;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.fragments.*;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TheActivity extends ActionBarActivity {
    private final List<ContainerFragment> fragments;

    public TheActivity() {
        fragments = new ArrayList<>();
        fragments.add(new ContainerFragment(new ManageInventoryFragment()));
        fragments.add(new ContainerFragment(new AvailableRecipesFragment()));
        fragments.add(new ContainerFragment(new BrowseFavoritesFragment()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_layout);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ThePagerAdapter(getSupportFragmentManager(), fragments));

        final AtomicBoolean created = new AtomicBoolean();
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                ContainerFragment container = fragments.get(tab.getPosition());
                container.getTopFragment().setMenuVisibility(true);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                ContainerFragment container = fragments.get(tab.getPosition());
                container.getTopFragment().setMenuVisibility(false);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        };

        ActionBar.Tab inventoryTab = actionBar.newTab().setText("Inventory").setTabListener(tabListener);
        ActionBar.Tab availableRecipesTab = actionBar.newTab().setText("Recipes").setTabListener(tabListener);
        ActionBar.Tab favoritesTab = actionBar.newTab().setText("Favorites").setTabListener(tabListener);

        actionBar.addTab(inventoryTab);
        actionBar.addTab(availableRecipesTab);
        actionBar.addTab(favoritesTab);
        actionBar.selectTab(inventoryTab);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        created.set(true);
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
                getFocusedContainer().pushFragment(new ViewRecipeFragment(recipe));
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
                getFocusedContainer().pushFragment(new ViewSearchResultsFragment(query));
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

    private ContainerFragment getFocusedContainer() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        return fragments.get(viewPager.getCurrentItem());
    }
}
