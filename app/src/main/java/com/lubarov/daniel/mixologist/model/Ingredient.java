package com.lubarov.daniel.mixologist.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A recipe category.
 */
public enum Ingredient {
    ABSINTHE("absinthe", "Absinthe", "absinth", "absynthe", "absenta", "absinth"),

    // Bitters
    ANGOSTURA_BITTERS("Angostura bitters", "Angostura Bitters"),
    ORANGE_BITTERS("orange bitters", "Orange Bitters"),
    PEACH_BITTERS("peach bitters", "Peach Bitters"),
    PEYCHAUDS_BITTERS("peychaud's Bitters", "Peychaud's Bitters"),
    CAMPARI("Campari", "Campari", "bitters"),

    BRANDY("brandy", "Brandy"),
    PISCO("pisco", "Pisco", "brandy"),
    APRICOT_BRANDY("apricot brandy", "Apricot Brandy"),
    COGNAC("cognac", "Cognac", "brandy"),
    KIRSCH("kirsch", "Kirsch", "brandy", "kirschwasser"),
    CALVADOS("calvados", "Calvados", "apple", "cider"),

    COFFEE("coffee", "Coffee"),
    COFFEE_LIQUEUR("coffee liqueur", "Coffee Liqueur", "kahlua"),

    BAILEYS_IRISH_CREAH("Bailey's", "Bailey's Irish Cream"),

    TRIPLE_SEC("triple sec", "Triple Sec"),
    CURACAO("curaćao", "Curaćao"),

    // Misc liqueurs
    MARASCHINO("maraschino", "Maraschino"),
    GALLIANO("Galliano", "Galliano"),
    CACHACA("Cachaça", "Cachaça"),
    AMARETTO("amaretto", "Amaretto", "disaronno"),
    RASPBERRY_LIQUEUR("raspberry liqueur", "Raspberry Liqueur"),
    BLACKBERRY_LIQUEUR("blackberry liqueur", "Blackberry Liqueur"),
    CHERRY_LIQUEUR("cherry liqueur", "Cherry Liqueur"),
    DRAMBUIE("Drambuie", "Drambuie"),
    PEACH_SCHNAPPS("peach schnapps", "Peach Schnapps"),
    BENEDICTINE("Bénédictine", "Bénédictine"),
    APEROL("Aperol", "Aperol"),

    CREME_DE_CACAO("créme de cacao", "Créme de Cacao (Chocolate Liqueur)"),
    CREME_DE_CASSIS("créme de cassis", "Créme de Cassis (Blackcurrant Liqueur)"),
    CREME_DE_MENTHE("crème de menthe", "Crème de Menthe (Mint Liqueur)"),

    GIN("gin", "Gin"),

    WHITE_RUM("white rum", "White Rum"),
    GOLD_RUM("gold rum", "Gold Rum"),
    DARK_RUM("dark rum", "Dark Rum"),

    TEQUILA("tequila", "Tequila"),

    VODKA("vodka", "Vodka"),
    VODKA_CITRON("vodka citron", "Vodka Citron"),

    SCOTCH_WHISKY("Scotch", "Scotch Whisky", "whiskey"),
    BOURBON_WHISKY("bourbon", "Bourbon Whisky", "whiskey"),
    RYE_WHISKY("rye", "Rye Whisky", "whiskey"),
    IRISH_WHISKY("Irish whisky", "Irish Whisky", "whiskey"),

    // Wines
    RED_WINE("red wine", "Red Wine"),
    WHITE_WINE("white wine", "White Wine"),
    SPARKLING_WINE("sparkling wine", "Sparkling Wine", "champagne", "prosecco"),
    DRY_VERMOUTH("dry vermouth", "Dry Vermouth (White)"),
    SWEET_RED_VERMOUTH("sweet red vermouth", "Sweet Vermouth (Red)"),
    SWEET_WHITE_VERMOUTH("sweet vermouth", "Sweet Vermouth (White)"),
    RED_PORT("red port", "Red Port"),
    WHITE_PORT("white port", "White Port"),
    LILLET("Lillet", "Lillet"),

    COLA("cola", "Cola", "coca", "pepsi"),
    GINGER_ALE("ginger ale", "Ginger Ale"),
    GINGER_BEER("ginger beer", "Ginger Beer"),

    // Fruits & vegetables
    ORANGES("oranges", "Oranges / Orange Juice"),
    LEMONS("lemons", "Lemons / Lemon Juice"),
    LIMES("limes", "Limes / Lime Juice"),
    PINEAPPLES("pineapples", "Pineapples / Pineapple Juice"),
    PEACHES("peaches", "Peaches / Peace Juice"),
    TOMATOES("tomatoes", "Tomatoes / Tomato Juice"),
    CRANBERRIES("cranberries", "Cranberries / Cranberry Juice"),
    OLIVES("olives", "Olives / Olive Juice"),
    GRAPEFRUITS("grapefruits", "Grapefruits / Grapefruit Juice"),
    ONIONS("onions", "Onions"),
    MARASCHINO_CHERRIES("maraschino cherries", "Maraschino Cherries"),

    // Sugars/syrups
    SUGAR_SYRUP("sugar", "Sugar / Sugar Syrup", "simple"),
    GOMME_SYRUP("homme syrup", "Gomme Syrup", "gum"),
    ORGEAT_SYRUP("orgeat syrup", "Orgeat Syrup"),
    STRAWBERRY_SYRUP("strawberry syrup", "Strawberry Syrup"),
    RASPBERRY_SYRUP("raspberry syrup", "Raspberry Syrup"),
    HONEY("honey", "Honey"),
    AGAVE_NECTAR("agave nectar", "Agave Nectar"),
    GRENADINE("grenadine", "Grenadine"),

    // Misc
    SODA_WATER("soda water", "Soda Water", "sparkling", "fizzy", "carbonated"),
    ORANGE_FLOWER_WATER("orange flower water", "Orange Flower Water"),
    EGGS("eggs", "Eggs"),
    MILK("milk", "Milk"),
    CREAM("cream", "Cream"),
    VANILLA("vanilla", "Vanilla"),
    MINT_LEAVES("mint", "Mint Leaves"),
    CHILI_PEPPERS("chili peppers", "Chili Peppers"),
    WORCESTERSHIRE_SAUCE("worcestershire sauce", "Worcestershire Sauce"),
    TABASCO("tabasco", "Tabasco"),
    SALT("salt", "Salt"),
    CELERY_SALT("celery salt", "Celery Salt"),
    PEPPER("pepper", "Pepper"),
    NUTMEG("nutmeg", "Nutmeg"),
    ;

    private final String shortName;
    private final String longName;
    private final Set<String> keywords;

    Ingredient(String shortName, String longName, String... keywords) {
        this.shortName = shortName;
        this.longName = longName;
        this.keywords = new HashSet<>(keywords.length);
        Collections.addAll(this.keywords, keywords);
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    @Override
    public String toString() {
        return longName;
    }
}
