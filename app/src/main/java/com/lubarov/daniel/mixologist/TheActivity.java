package com.lubarov.daniel.mixologist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

public class TheActivity extends ActionBarActivity {
    private final List<ContainerFragment> fragments;

    public TheActivity() {
        fragments = new ArrayList<>();
        fragments.add(new ContainerFragment(new BrowseCategoriesFragment()));
        fragments.add(new ContainerFragment(new BrowseFavoritesFragment()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_layout);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ActionBar actionBar = getSupportActionBar();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
        };

        ActionBar.Tab categoriesTab = actionBar.newTab().setText("Categories").setTabListener(tabListener);
        ActionBar.Tab favoritesTab = actionBar.newTab().setText("Favorites").setTabListener(tabListener);

        actionBar.addTab(categoriesTab);
        actionBar.addTab(favoritesTab);
        actionBar.selectTab(categoriesTab);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
    }

    @Override
    public void onBackPressed() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ContainerFragment fragment = fragments.get(viewPager.getCurrentItem());
        if (!fragment.popFragment())
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global_actions, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchViewConfigurer.configure(this, searchView);
        return super.onCreateOptionsMenu(menu);
    }
}
