package com.lubarov.daniel.mixologist.fragments;

/**
 * A recipe for browsing all categories of recipes.
 */
/*public class BrowseCategoriesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browse_categories_fragment, container, false);

        ListView categoryList = (ListView) view.findViewById(R.id.categories);
        categoryList.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, Ingredient.values()));
        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ContainerFragment) getParentFragment()).pushFragment(
                        new BrowseCategoryFragment(Ingredient.values()[position]));
            }
        });
        return view;
    }
}*/