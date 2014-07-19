package com.lubarov.daniel.mixologist.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lubarov.daniel.mixologist.R;

public class ContainerFragment extends Fragment {
    public static ContainerFragment create(Class<? extends Fragment> initialContent) {
        Bundle args = new Bundle();
        args.putSerializable("initial_content_type", initialContent);
        ContainerFragment container = new ContainerFragment();
        container.setArguments(args);
        return container;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.container_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        @SuppressWarnings("unchecked")
        Class<? extends Fragment> initialContentType = (Class<? extends Fragment>)
                getArguments().getSerializable("initial_content_type");
        Fragment initialContent;
        try {
            initialContent = initialContentType.newInstance();
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        Log.e("test", String.format("activity=%s, container=%s, content=%s", getActivity(), this, initialContent)); // TODO
        transaction.replace(R.id.container_framelayout, initialContent);
        transaction.commit();
    }

    public void pushFragment(Fragment newContent) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.container_framelayout, newContent);
        transaction.commit();
    }

    public boolean popFragment() {
        return getChildFragmentManager().popBackStackImmediate();
    }

    // TODO
//    @Override
//    public void onDetach() {
//        // See http://stackoverflow.com/a/15656428/714009
//        super.onDetach();
//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
