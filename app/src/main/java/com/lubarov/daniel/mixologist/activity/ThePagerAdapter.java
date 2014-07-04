package com.lubarov.daniel.mixologist.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.lubarov.daniel.mixologist.ContainerFragment;

import java.util.List;

public class ThePagerAdapter extends FragmentPagerAdapter {
    private final List<ContainerFragment> fragments;

    public ThePagerAdapter(FragmentManager fragmentManager, List<ContainerFragment> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    // A hack for refreshing views when tabs are changed. Inefficient but good enough for now.
    // See http://stackoverflow.com/a/7287121/714009
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
