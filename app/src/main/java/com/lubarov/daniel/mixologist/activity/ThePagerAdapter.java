package com.lubarov.daniel.mixologist.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lubarov.daniel.mixologist.fragments.AvailableRecipesFragment;
import com.lubarov.daniel.mixologist.fragments.BrowseFavoritesFragment;
import com.lubarov.daniel.mixologist.fragments.ManageIngredientsFragment;

class ThePagerAdapter extends FragmentPagerAdapter {
    ThePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ManageIngredientsFragment();
            case 1:
                return new AvailableRecipesFragment();
            case 2:
                return new BrowseFavoritesFragment();
            default:
                throw new IllegalArgumentException("Unexpected tab position: " + position);
        }
    }

    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Inventory";
            case 1:
                return "Recipes";
            case 2:
                return "Favorites";
            default:
                throw new IllegalArgumentException("Unexpected tab position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
