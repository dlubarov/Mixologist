package com.lubarov.daniel.mixologist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContainerFragment extends Fragment {
    private final Fragment initialFragment;
    private boolean viewInitialized = false;

    public ContainerFragment(Fragment initialFragment) {
        this.initialFragment = initialFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.container_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!viewInitialized) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.container_framelayout, initialFragment);
            transaction.commit();
            viewInitialized = true;
        }
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.replace(R.id.container_framelayout, fragment);
        transaction.commit();
    }

    public boolean popFragment() {
        return getChildFragmentManager().popBackStackImmediate();
    }
}
