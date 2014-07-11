package com.lubarov.daniel.mixologist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lubarov.daniel.mixologist.R;

import java.util.Deque;
import java.util.LinkedList;

public class ContainerFragment extends Fragment {
    private final Deque<Fragment> stack;

    public ContainerFragment(Fragment initialContent) {
        this.stack = new LinkedList<>();
        stack.add(initialContent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.container_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_framelayout, stack.getFirst());
        transaction.commit();
    }

    public void pushFragment(Fragment newContent) {
        stack.addLast(newContent);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.container_framelayout, newContent);
        transaction.commit();
    }

    public boolean popFragment() {
        if (!getChildFragmentManager().popBackStackImmediate())
            return false;
        stack.pop();
        return true;
    }

    public Fragment getTopFragment() {
        return stack.getLast();
    }
}
