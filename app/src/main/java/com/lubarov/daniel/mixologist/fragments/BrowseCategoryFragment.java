package com.lubarov.daniel.mixologist.fragments;

/**
 * A fragment for browsing a collection of recipes. Takes an optional category.
 */
/*public class BrowseCategoryFragment extends Fragment {
    private final Ingredient ingredient;

    public BrowseCategoryFragment(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browse_recipes_fragment, container, false);

        final List<Recipe> recipesToDisplay = new ArrayList<>();
        for (Recipe recipe : RecipeData.ALL_RECIPES)
            if (recipe.getRequiredIngredients().contains(ingredient))
                recipesToDisplay.add(recipe);

        GridView recipeGrid = (GridView) view.findViewById(R.id.recipes);
        recipeGrid.setNumColumns(GridSizer.getDesiredNumCols(getActivity().getWindowManager()));
        recipeGrid.setAdapter(new RecipeButtonAdapter(getActivity(), recipesToDisplay));
        recipeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ContainerFragment) getParentFragment()).pushFragment(
                        new ViewRecipeFragment(recipesToDisplay.get(position)));
            }
        });

        return view;
    }
}*/
