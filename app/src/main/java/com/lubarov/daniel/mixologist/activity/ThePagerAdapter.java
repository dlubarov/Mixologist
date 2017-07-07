package com.lubarov.daniel.mixologist.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.lubarov.daniel.mixologist.fragments.AvailableRecipesFragment;
import com.lubarov.daniel.mixologist.fragments.BrowseFavoritesFragment;
import com.lubarov.daniel.mixologist.fragments.ManageIngredientsFragment;

import java.lang.ref.WeakReference;

class ThePagerAdapter extends FragmentPagerAdapter {
    private final SparseArray<WeakReference<ContainerFragment>> registeredFragments = new SparseArray<>();

    ThePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Class<? extends Fragment> contentClass;
        switch (position) {
            case 0:
                contentClass = ManageIngredientsFragment.class;
                break;
            case 1:
                contentClass = AvailableRecipesFragment.class;
                break;
            case 2:
                contentClass = BrowseFavoritesFragment.class;
                break;
            default:
                throw new IllegalArgumentException("Unexpected position: " + position);
        }
        return ContainerFragment.create(contentClass);
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ContainerFragment fragment = (ContainerFragment) super.instantiateItem(container, position);
        registeredFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    Fragment getRegisteredFragment(int position) {
        WeakReference<ContainerFragment> container = registeredFragments.get(position);
        return container != null ? container.get() : null;
    }
}
