package com.lubarov.daniel.mixologist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeData {
    public static final List<Recipe> ALL_RECIPES = Arrays.asList(
            new Recipe("Alexander",
                    Arrays.asList("3 cl cognac", "3 cl créme de cacao (brown)", "3 cl cream"),
                    Arrays.asList(Category.BRANDY, Category.CHOCOLATE),
                    Arrays.asList(
                            "Shake and strain into a chilled cocktail glass.",
                            "Sprinkle with ground nutmeg."),
                    R.drawable.brandy_alexander, "Jason Lam"),

            new Recipe("Americano",
                    Arrays.asList("3 cl Campari", "3 cl Red vermouth", "A splash of soda water"),
                    Arrays.asList(Category.VERMOUTH),
                    Arrays.asList(
                            "Mix the ingredients directly in an old-fashioned glass filled with ice-cubes.",
                            "Add a splash of soda water.",
                            "Garnish with half orange slice."),
                    R.drawable.americano, "Conrad and Peter"),

            new Recipe("Angel Face",
                    Arrays.asList("3 cl gin", "3 cl Apricot brandy", "3 cl Calvados, or Applejack"),
                    Arrays.asList(Category.GIN, Category.BRANDY),
                    Arrays.asList(
                            "Pour all ingredients into a shaker with ice.",
                            "Shake.",
                            "Strain into a cocktail glass."),
                    R.drawable.angel_face, "Experiment 33"),

            new Recipe("Aviation",
                    Arrays.asList("4.5 cl gin", "1.5 cl Maraschino", "1.5 cl lemon juice"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList("Shake and strain into a chilled cocktail glass."),
                    R.drawable.aviation, "Dana Robinson"),

            new Recipe("Bacardi",
                    Arrays.asList("4.5 cl Bacardi rum white", "2 cl lime juice", "1 cl grenadine"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList(
                            "Pour all ingredients into shaker with ice cubes.",
                            "Shake well.",
                            "Strain into chilled cocktail glass."),
                    R.drawable.bacardi, "Dinah Sanders"),

            new Recipe("Barracuda",
                    Arrays.asList("4.5 cl gold rum", "1.5 cl Galliano", "6 cl pineapple juice", "1 dash lime juice",
                            "Prosecco"),
                    Arrays.asList(Category.RUM, Category.WINE),
                    Arrays.asList(
                            "Fill a chilled highball glass with ice cubes.",
                            "Add galliano, grenadine, light rum, lime juice and pineapple juice.",
                            "Top with Prosecco."),
                    R.drawable.barracuda, "How Gauche!"),

            new Recipe("Between the Sheets",
                    Arrays.asList("3 cl white rum", "3 cl cognac", "3 cl triple sec", "2 cl lemon juice"),
                    Arrays.asList(Category.RUM, Category.BRANDY, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Pour all ingredients into shaker with ice cubes.",
                            "Shake.",
                            "Strain into chilled cocktail glass."),
                    R.drawable.between_the_sheets, "Dinah Sanders"),

            new Recipe("Bellini",
                    Arrays.asList("10 cl Prosecco", "5 cl peach puree"),
                    Arrays.asList(Category.WINE),
                    Arrays.asList(
                            "Pour peach puree into chilled glass and add sparkling wine.",
                            "Stir gently."),
                    R.drawable.bellini, "crazybobbles"),

            new Recipe("Black Russian",
                    Arrays.asList("5 cl vodka", "2 cl Coffee liqueur"),
                    Arrays.asList(Category.VODKA, Category.COFFEE),
                    Arrays.asList(
                            "Pour the ingredients into the old fashioned-glass filled with ice cubes.",
                            "Stir gently.",
                            "Note: for white Russian, float cream on the top and stir gently."),
                    R.drawable.black_russian, null),

            new Recipe("Bloody Mary",
                    Arrays.asList("4.5 cl vodka", "9 cl tomato juice", "1.5 cl lemon juice",
                            "2-3 dashes Worcestershire Sauce", "Tabasco", "celery salt", "pepper"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Pour all ingredients into highball glass.",
                            "Stir gently.",
                            "Garnish with celery and lemon wedge (optional)."),
                    R.drawable.bloody_mary, "William Clifford"),

            new Recipe("Bramble",
                    Arrays.asList("4 cl gin", "1.5 cl lemon juice", "1 cl sugar syrup", "1.5 cl blackberry liqueur"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList(
                            "Build over crushed ice, in a rock glass.",
                            "Stir, then pour the blackberry liqueur over the top of the drink in a circular fashion.",
                            "Garnish with a lemon slice, and two blackberries."),
                    R.drawable.bramble, "Jonny Hughes"),

            new Recipe("B52",
                    Arrays.asList("2 cl Kahlua", "2 cl Bailey's Irish Cream", "2 cl Grand Marnier"),
                    Arrays.asList(Category.COFFEE, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Layer ingredients one at a time starting with Kahlua, followed by Bailey's Irish Cream and top with Grand Marnier.",
                            "Flame the Grand Marnier. Serve while the flame is still on, accompanied with a straw on side plate."),
                    R.drawable.b52, "scorps80"),

            new Recipe("Caipirinha",
                    Arrays.asList("5 cl Cachaca", "half lime cut into 4 wedges", "2 teaspoon sugar"),
                    Arrays.asList(Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Place lime and suger in old fashion glass and muddle.",
                            "Fill glass with ice and Cachaca."),
                    R.drawable.caipirinha, "Achim Schleuning"),

            new Recipe("Casino",
                    Arrays.asList("4 cl Old Tom gin", "1 cl Maraschino", "1 cl orange bitters", "1 cl lemon juice"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList(
                            "Pour all ingredients into shaker with ice cubes.",
                            "Shake well.",
                            "Strain into chilled cocktail glass.",
                            "Garnish with a lemon twist and a maraschino cherry."),
                    R.drawable.vesper, // Close enough :-)
                    "TabascoMan77"),

            new Recipe("Champagne Cocktail",
                    Arrays.asList("9 cl chilled Champagne", "1 cl cognac", "2 dashes Angostura bitters",
                            "1 sugar cube"),
                    Arrays.asList(Category.WINE, Category.BRANDY),
                    Arrays.asList(
                            "Add dash of Angostura bitter onto sugar cube and drop it into champagne flute.",
                            "Add cognac followed by pouring gently chilled champagne.",
                            "Garnish with orange slice and maraschino cherry."),
                    R.drawable.champagne_cocktail, "Addison Berry"),

            new Recipe("Clover Club",
                    Arrays.asList("4.5 cl gin", "1.5 cl raspberry syrup", "1.5 cl lemon juice",
                            "few drops of egg white"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList(
                            "Pour all ingredients into cocktail shaker filled with ice.",
                            "Shake well.",
                            "Strain into cocktail glass."),
                    R.drawable.clover_club, "Reese Lloyd (flickr)"),

            new Recipe("Cosmopolitan",
                    Arrays.asList("4 cl citron vodka", "1.5 cl Cointreau", "1.5 cl lime juice", "3 cl cranberry juice"),
                    Arrays.asList(Category.VODKA, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Shake all ingredients in cocktail shaker filled with ice.",
                            "Strain into a large cocktail glass",
                            "Garnish with lime slice."),
                    R.drawable.cosmopolitan, "Ralph Daily"),

            new Recipe("Cuba Libre",
                    Arrays.asList("5 cl white rum", "12 cl cola", "1 cl lime juice"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList(
                            "Build all ingredients in a highball glass filled with ice.",
                            "Garnish with lime wedge."),
                    R.drawable.cuba_libre, "Martin Belam"),

            new Recipe("Daiquiri",
                    Arrays.asList("4.5 cl white rum", "2.5 cl lime juice", "1.5 cl simple syrup"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList("Shake and strain into a cocktail glass."),
                    R.drawable.daiquiri, "Renee Suen"),

            new Recipe("Dark 'n' Stormy",
                    Arrays.asList("6 cl dark rum", "10 cl ginger beer"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList(
                            "In a highball glass filled with ice add 6cl dark rum and top with ginger beer.",
                            "Garnish with lime wedge."),
                    R.drawable.dark_n_stormy, "Michelle Schrank"),

            new Recipe("Derby",
                    Arrays.asList("6 cl gin", "2 drops peach bitters", "2 mint leafs"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList(
                            "Pour all ingredients into a mixing glass with ice.",
                            "Stir.",
                            "Strain into a cocktail glass.",
                            "Garnish with mint leaves in the drink."),
                    R.drawable.derby, "mobil'homme"),

            new Recipe("Dirty Martini",
                    Arrays.asList("6 cl vodka", "1 cl dry vermouth", "1 cl olive juice"),
                    Arrays.asList(Category.VODKA, Category.VERMOUTH),
                    Arrays.asList(
                            "Pour all ingredients into mixing glass with ice cubes.",
                            "Stir well.",
                            "Strain in chilled martini glass.",
                            "Garnish with green olive."),
                    R.drawable.dirty_martini, "Dale Gillard"),

            new Recipe("Dry Martini",
                    Arrays.asList("6 cl gin", "1 cl dry vermouth"),
                    Arrays.asList(Category.GIN, Category.VERMOUTH),
                    Arrays.asList(
                            "Pour all ingredients into mixing glass with ice cubes.",
                            "Stir well.",
                            "Strain in chilled martini glass.",
                            "Squeeze oil from lemon peel onto the drink, or garnish with olive."),
                    R.drawable.martini, "Tom Hilton"),

            new Recipe("Espresso Martini",
                    Arrays.asList("5 cl vodka", "1 cl Kahlúa", "sugar syrup (to taste)", "1 short strong espresso"),
                    Arrays.asList(Category.VODKA, Category.COFFEE),
                    Arrays.asList("Shake and strain into a chilled cocktail glass."),
                    R.drawable.espresso_martini, "Tracy Benjamin"),

            new Recipe("French Connection",
                    Arrays.asList("3.5 cl cognac", "3.5 cl DiSaronno"),
                    Arrays.asList(Category.BRANDY),
                    Arrays.asList(
                            "Pour all ingredients directly into old fashioned glass filled with ice cubes.",
                            "Stir gently."),
                    R.drawable.french_connection, "Sinclair77"),

            new Recipe("French Martini",
                    Arrays.asList("4.5 cl vodka", "1.5 cl Raspberry liqueur", "1.5 cl pineapple juice"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Pour all ingredients into mixing glass with ice cubes. Stir well.",
                            "Strain in chilled cocktail glass.",
                            "Squeeze oil from lemon peel onto the drink."),
                    R.drawable.french_martini, "Stuart Webster"),

            new Recipe("French 75",
                    Arrays.asList("3 cl gin", "1.5 cl lemon juice", "2 dashes sugar syrup", "6 cl Champagne"),
                    Arrays.asList(Category.GIN, Category.WINE),
                    Arrays.asList(
                            "Pour all the ingredients, except champagne, into a shaker. Shake.",
                            "Strain into a champagne flute.",
                            "Top up with champagne. Stir gently."),
                    R.drawable.french_75, "Kenn Wilson (flickr)"),

            new Recipe("Gin Fizz",
                    Arrays.asList("4.5 cl gin", "3 cl lemon juice", "1 cl sugar syrup", "8 cl soda water"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList(
                            "Shake all ingredients with ice cubes, except soda water. Pour into tumbler.",
                            "Top with soda water.",
                            "Garnish with lemon slice."),
                    R.drawable.gin_fizz, "Lincoln Smith"),

            new Recipe("Godfather",
                    Arrays.asList("3.5 cl Scotch", "3.5 cl DiSaronno"),
                    Arrays.asList(Category.WHISKY),
                    Arrays.asList(
                            "Pour all ingredients directly into old fashioned glass filled with ice cubes.",
                            "Stir gently."),
                    R.drawable.godfather, "Reese Lloyd (flickr)"),

            new Recipe("Godmother",
                    Arrays.asList("3.5 cl vodka", "3.5 cl DiSaronno"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Pour all ingredients directly into old fashioned glass filled with ice cubes.",
                            "Stir gently."),
                    R.drawable.godmother, "Sam Smith"),

            new Recipe("Golden Dream",
                    Arrays.asList("2 cl Galliano", "2 cl triple sec", "2 cl orange juice", "1 cl cream"),
                    Arrays.asList(Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Pour all ingredients into shaker filled with ice.",
                            "Shake briskly for few seconds.",
                            "Strain into chilled cocktail glass."),
                    R.drawable.golden_dream, "James Jacks"),

            new Recipe("Grasshopper",
                    Arrays.asList("3 cl créme de cacao (white)", "3 cl créme de menthe (green)", "3 cl cream"),
                    Arrays.asList(Category.CHOCOLATE),
                    Arrays.asList(
                            "Pour all ingredients into shaker filled with ice.",
                            "Shake briskly for few seconds.",
                            "Strain into chilled cocktail glass."),
                    R.drawable.grasshopper, "TexasDex"),

            new Recipe("Harvey Wallbanger",
                    Arrays.asList("4.5 cl vodka", "1.5 cl Galliano (to float on drink)", "9 cl orange juice"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Pour vodka and orange juice into a highball glass filled with ice.",
                            "Stir gently and float Galliano on top. Garnish with orange slices and cherry."),
                    R.drawable.harvey_wallbanger, "Steven Labinski"),

            new Recipe("Hemingway Special",
                    Arrays.asList("6 cl white rum", "4 cl Grapefruit juice", "1.5 cl Maraschino", "1.5 cl lime juice"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList(
                            "Pour all ingredients into a shaker with ice. Shake.",
                            "Strain into a double cocktail glass."),
                    R.drawable.hemingway_special, "Simon Leong"),

            new Recipe("Horse's Neck",
                    Arrays.asList("4 cl Brandy", "12 cl ginger ale", "dash of Angostura bitters (optional)"),
                    Arrays.asList(Category.BRANDY),
                    Arrays.asList(
                            "Pour brandy and ginger ale directly into highball glass with ice cubes.",
                            "Stir gently.",
                            "Garnish with rind of one lemon spiral.",
                            "If desired, add dashes of Angostura bitters."),
                    R.drawable.horses_neck, "Kevin Tao"),

            new Recipe("Irish Coffee",
                    Arrays.asList("4 cl Irish whisky", "9 cl hot coffee", "3 cl cream", "1 teaspoon brown sugar"),
                    Arrays.asList(Category.WHISKY, Category.COFFEE),
                    Arrays.asList(
                            "Heat the coffee, whisky and sugar; do not boil.",
                            "Pour into glass and top with cream; serve hot."),
                    R.drawable.irish_coffee, "Visitor7 (Wikimedia Commons)"),

            new Recipe("John Collins",
                    Arrays.asList("4.5 cl gin", "3 cl lemon juice", "1.5 cl sugar syrup", "6 cl soda water"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList(
                            "Pour all ingredients directly into highball glass filled with ice.",
                            "Stir gently.",
                            "Garnish with lemon slice and maraschino cherry.",
                            "Add a dash of Angostura bitters."),
                    R.drawable.tom_collins, "Daniel Nguyen"),

            new Recipe("Kamikaze",
                    Arrays.asList("3 cl vodka", "3 cl triple sec", "3 cl lime juice"),
                    Arrays.asList(Category.VODKA, Category.FRUIT_LIQUEUR),
                    Arrays.asList("Shake and strain into a chilled cocktail glass."),
                    R.drawable.kamikaze, "Momoji3"),

            new Recipe("Kir",
                    Arrays.asList("9 cl dry white Wine", "1 cl créme de cassis"),
                    Arrays.asList(Category.WINE),
                    Arrays.asList(
                            "Pour créme de cassis into glass, top up with white wine.",
                            "For Kir Royal: Use champagne instead of white wine."),
                    R.drawable.kir, "Stuart Webster"),

            new Recipe("Lemon Drop Martini",
                    Arrays.asList("2.5 cl vodka citron", "2 cl triple sec", "1.5 cl lemon juice"),
                    Arrays.asList(Category.VODKA, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Shake and strain into a chilled cocktail glass rimmed with sugar.",
                            "Garnish with a slice of lemon."),
                    R.drawable.dirty_martini, "Dale Gillard"),

            new Recipe("Long Island Iced Tea",
                    Arrays.asList("1.5 cl Tequila", "1.5 cl vodka", "1.5 cl white rum", "1.5 cl triple sec",
                            "1.5 cl gin", "2.5 cl lemon juice", "3.0 cl Gomme syrup", "1 dash cola"),
                    Arrays.asList(Category.TEQUILA, Category.VODKA, Category.RUM, Category.FRUIT_LIQUEUR,
                            Category.GIN),
                    Arrays.asList(
                            "Add all ingredients into highball glass filled with ice.",
                            "Stir gently. Garnish with lemon spiral. Serve with straw."),
                    R.drawable.long_island_iced_tea, "Alisdair McDiarmid"),

            new Recipe("Mai-Tai",
                    Arrays.asList("4 cl white rum", "2 cl dark rum", "1.5 cl orange Curaćao", "1.5 cl Orgeat syrup",
                            "1 cl lime juice"),
                    Arrays.asList(Category.RUM, Category.RUM, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Shake and strain into highball glass.",
                            "Garnish with pineapple spear, mint leaves and lime peel.",
                            "Serve with straw."),
                    R.drawable.mai_tai, "duluoz cats (flickr)"),

            new Recipe("Manhattan",
                    Arrays.asList("5 cl rye whisky", "2 cl red vermouth", "1 dash Angostura bitters"),
                    Arrays.asList(Category.WHISKY, Category.VERMOUTH),
                    Arrays.asList(
                            "Pour all ingredients into mixing glass with ice cubes.",
                            "Stir well.",
                            "Strain into chilled cocktail glass.",
                            "Garnish with cocktail cherry."),
                    R.drawable.manhattan, "Babbage (Wikimedia Commons)"),

            new Recipe("Margarita",
                    Arrays.asList("3.5 cl Tequila", "2 cl Cointreau", "1.5 cl lime juice"),
                    Arrays.asList(Category.TEQUILA, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Pour all ingredients into shaker with ice.",
                            "Shake well and strain into cocktail glass rimmed with salt."),
                    R.drawable.margarita, "Zalmac (Wikimedia Commons)"),

            new Recipe("Mary Pickford",
                    Arrays.asList("6 cl white rum", "1 cl Maraschino", "6 cl pineapple juice",
                            "1 cl grenadine syrup"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList("Shake and strain into a chilled large cocktail glass."),
                    R.drawable.mary_pickford, "Vince Keenan"),

            new Recipe("Mimosa",
                    Arrays.asList("7.5 cl Champagne", "7.5 cl orange juice"),
                    Arrays.asList(Category.WINE),
                    Arrays.asList(
                            "Pour orange juice into flute and gently pour Champagne. Stir gently.",
                            "Garnish with orange twist (optional)."),
                    R.drawable.mimosa, "vxla (flickr)"),

            new Recipe("Mint Julep",
                    Arrays.asList("6 cl bourbon whisky", "4 mint sprigs", "1 teaspoon powdered sugar",
                            "2 teaspoons water"),
                    Arrays.asList(Category.WHISKY),
                    Arrays.asList(
                            "In a highball glass gently muddle the mint, sugar and water.",
                            "Fill the glass with cracked ice, add bourbon and stir well until the glass is frost.",
                            "Garnish with a mint spring."),
                    R.drawable.mint_julep, "Ewan Munro"),

            new Recipe("Mojito",
                    Arrays.asList("4 cl white Cuban rum", "3 cl lime juice", "6 mint sprigs",
                            "2 teaspoons white sugar", "soda water"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList(
                            "Muddle mint springs with sugar and lime juice.",
                            "Add splash of soda water and fill glass with cracked ice.",
                            "Pour rum and top with soda water.",
                            "Garnish with spring of mint leaves and lemon slice.",
                            "Serve with straw."),
                    R.drawable.mojito, "janineomg (flickr)"),

            new Recipe("Monkey Gland",
                    Arrays.asList("5 cl gin", "3 cl orange juice", "2 drops absinthe", "2 drops grenadine"),
                    Arrays.asList(Category.GIN, Category.ABSINTHE),
                    Arrays.asList("Shake and strain into a chilled cocktail glass."),
                    R.drawable.monkey_gland, "Reese Lloyd (flickr)"),

            new Recipe("Moscow Mule",
                    Arrays.asList("4.5 cl vodka", "12 cl ginger beer", "0.5 cl lime juice"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Combine vodka and ginger beer in a highball glass filled with ice.",
                            "Add lime juice.",
                            "Stir gently and garnish with a lime slice."),
                    R.drawable.moscow_mule, "Travelstart South Africa (flickr)"),

            new Recipe("Negroni",
                    Arrays.asList("3 cl gin", "3 cl Campari", "3 cl sweet red vermouth"),
                    Arrays.asList(Category.GIN, Category.VERMOUTH),
                    Arrays.asList(
                            "Pour all ingredients directly into old-fashioned glass filled with ice.",
                            "Stir gently.",
                            "Garnish with half orange slice."),
                    R.drawable.negroni, "Jeremy Brooks"),

            new Recipe("Old Fashioned",
                    Arrays.asList("4.5 cl bourbon or rye whisky", "2 dashes Angostura bitters", "1 sugar cube",
                            "Few dashes plain water"),
                    Arrays.asList(Category.WHISKY),
                    Arrays.asList(
                            "Place sugar cube in old-fashioned glass and saturate with bitters.",
                            "Add a dash of plain water.",
                            "Muddle until dissolved.",
                            "Fill the glass with ice cubes and add whisky.",
                            "Garnish with orange slice and a cocktail cherry."),
                    R.drawable.old_fashioned, "Lindsey Turner"),

            new Recipe("Paradise",
                    Arrays.asList("3.5 cl gin", "2 cl Apricot Brandy", "1.5 cl orange juice"),
                    Arrays.asList(Category.GIN, Category.BRANDY),
                    Arrays.asList(
                            "Pour all ingredients into cocktail shaker filled with ice.",
                            "Shake and strain into chilled cocktail glass."),
                    R.drawable.paradise, "Mathias Holm"),

            new Recipe("Pina Colada",
                    Arrays.asList("3 cl white rum", "9 cl pineapple juice", "3 cl Coconut milk"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList(
                            "Blend all the ingredients with ice in a electric blender.",
                            "Pour into a large goblet or Hurricane glass and serve with straws.",
                            "Garnish with a slice of pineapple with a cocktail cherry."),
                    R.drawable.pina_colada, "Ruben I (flickr)"),

            new Recipe("Pisco Sour",
                    Arrays.asList("4.5 cl pisco", "3 cl lemon juice", "2 cl sugar syrup", "1 raw egg white"),
                    Arrays.asList(Category.BRANDY),
                    Arrays.asList(
                            "Shake and strain into a chilled champagne flute.",
                            "Dash some Angostura bitters on top."),
                    R.drawable.pisco_sour, "Dtarazona (Wikimedia Commons)"),

            new Recipe("Planter's Punch",
                    Arrays.asList("4.5 cl dark rum", "3.5 cl orange juice", "3.5 cl pineapple juice",
                            "2 cl lemon juice", "1 cl grenadine", "1 cl sugar syrup", "3-4 dashes Angostura bitters"),
                    Arrays.asList(Category.RUM),
                    Arrays.asList(
                            "Pour all ingredients, except the bitters, into shaker filled with ice.",
                            "Shake well.",
                            "Pour into large glass, filled with ice.",
                            "Add Angostura bitters, \"on top\".",
                            "Garnish with cocktail cherry and pineapple."),
                    R.drawable.planters_punch, "Reese Lloyd (flickr)"),

            new Recipe("Porto Flip",
                    Arrays.asList("1.5 cl Brandy", "4.5 cl Red Port", "1 cl Egg yolk"),
                    Arrays.asList(Category.BRANDY, Category.WINE),
                    Arrays.asList(
                            "Pour all ingredients into cocktail shaker filled with ice. Shake well.",
                            "Strain into cocktail glass.",
                            "Sprinkle with ground nutmeg."),
                    R.drawable.porto_flip, "Stuart Webster"),

            new Recipe("Ramos Fizz",
                    Arrays.asList("4.5 cl gin", "1.5 cl lime juice", "1.5 cl lemon juice", "3 cl sugar syrup",
                            "6 cl cream", "1 egg white", "3 dashes orange flower water", "2 drops vanilla extract",
                            "soda water"),
                    Arrays.asList(Category.GIN),
                    Arrays.asList(
                            "Pour all ingredients (except soda) in a mixing glass.",
                            "dry shake (no ice) for two minutes.",
                            "Add ice and hard shake for another minute.",
                            "Strain into a highball glass without ice.",
                            "Top with soda."),
                    R.drawable.ramos_fizz, "Reese Lloyd (flickr)"),

            new Recipe("Rose",
                    Arrays.asList("2 cl Kirsch", "4 cl dry vermouth", "3 dashes Strawberry syrup"),
                    Arrays.asList(Category.BRANDY, Category.VERMOUTH),
                    Arrays.asList("Stir all ingredients with ice and strain into a cocktail glass"),
                    R.drawable.rose, "Stuart Webster (flickr)"),

            new Recipe("Russian Spring Punch",
                    Arrays.asList("2.5 cl vodka", "2.5 cl lemon juice", "1.5 cl créme de cassis", "1 cl sugar syrup"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Shake the ingredients and pour into highball glass. Top with Sparkling wine.",
                            "Garnish with a lemon slice and a blackberry."),
                    R.drawable.russian_spring_punch, "IBA"),

            new Recipe("Rusty Nail",
                    Arrays.asList("4.5 cl Scotch whisky", "2.5 cl Drambuie"),
                    Arrays.asList(Category.WHISKY),
                    Arrays.asList(
                            "Pour all ingredients directly into old-fashioned glass filled with ice.",
                            "Stir gently.",
                            "Garnish with lemon twist."),
                    R.drawable.rusty_nail, "Reese Lloyd (flickr)"),

            new Recipe("Sazerac",
                    Arrays.asList("5 cl cognac", "1 cl absinthe", "1 sugar cube", "2 dashes Peychaud's bitters"),
                    Arrays.asList(Category.BRANDY, Category.ABSINTHE),
                    Arrays.asList(
                            "Rinse a chilled old-fashioned glass with the absinthe, add crushed ice and set it aside.",
                            "Stir the remaining ingredients over ice and set it aside.",
                            "Discard the ice and any excess absinthe from the prepared glass, and strain the drink into the glass.",
                            "Add the lemon peel for garnish."),
                    R.drawable.sazerac, "Göran Arvidso (flickr)"),

            new Recipe("Screwdriver",
                    Arrays.asList("5 cl vodka", "10 cl orange juice"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Pour all ingredients into a highball glass filled with ice.",
                            "Stir gently.",
                            "Garnish with a orange slice."),
                    R.drawable.screwdriver, "Muy Yum (flickr)"),

            new Recipe("Sea Breeze",
                    Arrays.asList("4 cl vodka", "12 cl cranberry juice", "3 cl Grapefruit juice"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Build all ingredients in a rock glass filled with ice.",
                            "Garnish with lime wedge."),
                    R.drawable.sea_breeze, "Måns Sandström (flickr)"),

            new Recipe("Sex on the Beach",
                    Arrays.asList("4 cl vodka", "2 cl peach schnapps", "4 cl cranberry juice", "4 cl orange juice"),
                    Arrays.asList(Category.VODKA),
                    Arrays.asList(
                            "Build all ingredients in a highball glass filled with ice.",
                            "Garnish with orange slice."),
                    R.drawable.sex_on_the_beach, "Robyn Lee (flickr)"),

            new Recipe("Sidecar",
                    Arrays.asList("5 cl cognac", "2 cl triple sec", "2 cl lemon juice"),
                    Arrays.asList(Category.BRANDY, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Pour all ingredients into cocktail shaker filled with ice.",
                            "Shake well and strain into cocktail glass."),
                    R.drawable.sidecar, "Joan Matelli (flickr)"),

            new Recipe("Singapore Sling",
                    Arrays.asList("3 cl gin", "1.5 cl cherry liqueur", "0.75 cl Cointreau", "0.75 cl DOM Bénédictine",
                            "12.0 cl pineapple juice", "1.5 cl lime juice", "1 cl grenadine", "1 dash Angostura bitters"),
                    Arrays.asList(Category.GIN, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Pour all ingredients into cocktail shaker filled with ice cubes.",
                            "Shake well.",
                            "Strain into highball glass.",
                            "Garnish with pineapple and cocktail cherry."),
                    R.drawable.singapore_sling, "Jo Morcom (flickr)"),

            new Recipe("Spritz",
                    Arrays.asList("6 cl Prosecco", "4 cl Aperol", "splash of soda water"),
                    Arrays.asList(Category.WINE),
                    Arrays.asList(
                            "Build into an old-fashioned glass filled with ice.",
                            "Top with a splash of soda water.",
                            "Garnish with half orange slice."),
                    R.drawable.spritz, "Ramiro Sánchez-Crespo (flickr)"),

            new Recipe("Stinger",
                    Arrays.asList("5 cl cognac", "2 cl créme de menthe (white)"),
                    Arrays.asList(Category.BRANDY),
                    Arrays.asList(
                            "Pour all ingredients into a mixing glass with ice. Stir.",
                            "Strain into a cocktail glass."),
                    R.drawable.stinger, "Kenn Wilson (flickr)"),

            new Recipe("Tequila Sunrise",
                    Arrays.asList("4.5 cl Tequila", "9 cl orange juice", "1.5 cl grenadine"),
                    Arrays.asList(Category.TEQUILA),
                    Arrays.asList(
                            "Pour tequila and orange juice directly into highball with ice cubes.",
                            "Add a splash of grenadine to create chromatic effect (sunrise), do not stir.",
                            "Garnish with orange slice and cherry."),
                    R.drawable.tequila_sunrise, "Dustin Williams (flickr)"),

            new Recipe("Tommy's Margarita",
                    Arrays.asList("4.5 cl Tequila", "1.5 cl lime juice", "2 bar spoons of agave nectar"),
                    Arrays.asList(Category.TEQUILA),
                    Arrays.asList("Shake and strain into a chilled cocktail glass."),
                    R.drawable.tommys_margarita, "spine (flickr)"),

            new Recipe("Tuxedo",
                    Arrays.asList("3 cl Old Tom gin", "3 cl dry vermouth", "1/2 bar spoon Maraschino",
                            "1/4 bar spoon absinthe", "3 dashes orange bitters"),
                    Arrays.asList(Category.GIN, Category.VERMOUTH, Category.ABSINTHE),
                    Arrays.asList(
                            "Stir all ingredients with ice and strain into cocktail glass.",
                            "Garnish with a cocktail cherry and a lemon zest twist."),
                    R.drawable.tuxedo_cocktail, "Foodie Buddha (flickr)"),

            new Recipe("Vampiro",
                    Arrays.asList("5 cl Tequila (silver)", "7 cl tomato juice", "3 cl orange juice",
                            "1 cl lime juice", "1 teaspoon clear honey", "Half slice onion finely chopped",
                            "Few slices red hot chili peppers", "Few drops Worchestershire sauce", "salt"),
                    Arrays.asList(Category.TEQUILA),
                    Arrays.asList(
                            "Pour all ingredients into a shaker filled with ice.",
                            "Shake well, to release the flavour of the chili.",
                            "Strain into a highball glass, filled with ice.",
                            "Garnish with a wedge of lime and a chili (green or red)."),
                    R.drawable.vampiro, "IBA"),

            new Recipe("Vesper",
                    Arrays.asList("6 cl gin", "1.5 cl vodka", "0.75 cl Lillet Blonde", "lemon twist (garnish)"),
                    Arrays.asList(Category.GIN, Category.VODKA),
                    Arrays.asList(
                            "Shake and strain into a chilled cocktail glass.",
                            "Add the garnish."),
                    R.drawable.vesper, "Coffee Geek (flickr)"),

            new Recipe("Whisky Sour",
                    Arrays.asList("4.5 cl bourbon whisky", "3.0 cl lemon juice", "1.5 cl sugar syrup",
                            "dash egg white (optional)"),
                    Arrays.asList(Category.WHISKY),
                    Arrays.asList(
                            "Shake with ice.",
                            "Strain into ice-filled old-fashioned glass."),
                    R.drawable.whisky_sour, "smoddelm (flickr)"),

            new Recipe("White Lady",
                    Arrays.asList("4 cl gin", "3 cl triple sec", "2 cl lemon juice"),
                    Arrays.asList(Category.GIN, Category.FRUIT_LIQUEUR),
                    Arrays.asList(
                            "Add all ingredients into cocktail shaker filled with ice.",
                            "Shake well and strain into large cocktail glass."),
                    R.drawable.white_lady, "Dinah Sours (flickr)"),

            new Recipe("Yellow Bird",
                    Arrays.asList("3 cl white rum", "1.5 cl Galliano", "1.5 cl triple sec", "1.5 cl lime juice"),
                    Arrays.asList(Category.RUM, Category.FRUIT_LIQUEUR),
                    Arrays.asList("Shake and strain into a chilled cocktail glass."),
                    R.drawable.yellow_bird, "queenkv (flickr)")
    );

    private static final Map<String, Recipe> RECIPES_BY_NAME;

    static {
        RECIPES_BY_NAME = new HashMap<>();
        for (Recipe recipe : ALL_RECIPES)
            RECIPES_BY_NAME.put(recipe.getName(), recipe);
    }

    public static Recipe getByName(String name) {
        Recipe recipe = RECIPES_BY_NAME.get(name);
        if (recipe == null)
            throw new IllegalArgumentException("No such recipe: " + name);
        return recipe;
    }

    private RecipeData() {}
}
