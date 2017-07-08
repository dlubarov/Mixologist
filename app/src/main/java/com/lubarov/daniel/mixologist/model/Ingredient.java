package com.lubarov.daniel.mixologist.model;

import android.content.Context;

import com.lubarov.daniel.mixologist.R;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A recipe category.
 */
public enum Ingredient {
    ABSINTHE(R.string.absinthe_short, R.string.absinthe_long, "absinth", "absynthe", "absenta", "absinth"),

    // Bitters
    ANGOSTURA_BITTERS(R.string.angostura_bitters_short, R.string.angostura_bitters_long),
    ORANGE_BITTERS(R.string.orange_bitters_short, R.string.orange_bitters_long),
    PEACH_BITTERS(R.string.peach_bitters_short, R.string.peach_bitters_long),
    PEYCHAUDS_BITTERS(R.string.peychauds_bitters_short, R.string.peychauds_bitters_long),
    CAMPARI(R.string.campari_short, R.string.campari_long, "bitters"),

    BRANDY(R.string.brandy_short, R.string.brandy_long),
    PISCO(R.string.pisco_short, R.string.pisco_long, "brandy"),
    APRICOT_BRANDY(R.string.apricot_brandy_short, R.string.apricot_brandy_long),
    COGNAC(R.string.cognac_short, R.string.cognac_long, "brandy"),
    KIRSCH(R.string.kirsch_short, R.string.kirsch_long, "brandy", "kirschwasser"),
    CALVADOS(R.string.calvados_short, R.string.calvados_long, "apple", "cider"),

    COFFEE(R.string.coffee_short, R.string.coffee_long),
    COFFEE_LIQUEUR(R.string.coffee_liqueur_short, R.string.coffee_liqueur_long, "kahlua"),

    IRISH_CREAM(R.string.irish_cream_short, R.string.irish_cream_long),

    TRIPLE_SEC(R.string.triple_sec_short, R.string.triple_sec_long),
    CURACAO(R.string.curacao_short, R.string.curacao_long),

    // Misc liqueurs
    MARASCHINO(R.string.maraschino_short, R.string.maraschino_long),
    GALLIANO(R.string.galliano_short, R.string.galliano_long),
    CACHACA(R.string.cachaca_short, R.string.cachaca_long),
    AMARETTO(R.string.amaretto_short, R.string.amaretto_long, "disaronno"),
    RASPBERRY_LIQUEUR(R.string.raspberry_liqueur_short, R.string.raspberry_liqueur_long),
    BLACKBERRY_LIQUEUR(R.string.blackberry_liqueur_short, R.string.blackberry_liqueur_long),
    CHERRY_LIQUEUR(R.string.cherry_liqueur_short, R.string.cherry_liqueur_long),
    DRAMBUIE(R.string.drambuie_short, R.string.drambuie_long),
    PEACH_SCHNAPPS(R.string.peach_schnapps_short, R.string.peach_schnapps_long),
    BENEDICTINE(R.string.benedictine_short, R.string.benedictine_long),
    APEROL(R.string.aperol_short, R.string.aperol_long),

    CREME_DE_CACAO(R.string.creme_de_cacao_short, R.string.creme_de_cacao_long, "chocolate"),
    CREME_DE_CASSIS(R.string.creme_de_cassis_short, R.string.creme_de_cassis_long, "blackcurrant"),
    CREME_DE_MENTHE(R.string.creme_de_menthe_short, R.string.creme_de_menthe_long, "mint"),

    GIN(R.string.gin_short, R.string.gin_long),

    WHITE_RUM(R.string.white_rum_short, R.string.white_rum_long),
    GOLD_RUM(R.string.gold_rum_short, R.string.gold_rum_long),
    DARK_RUM(R.string.dark_rum_short, R.string.dark_rum_long),

    TEQUILA(R.string.tequila_short, R.string.tequila_long),

    VODKA(R.string.vodka_short, R.string.vodka_long),
    VODKA_CITRON(R.string.vodka_citron_short, R.string.vodka_citron_long),

    SCOTCH_WHISKY(R.string.scotch_whisky_short, R.string.scotch_whisky_long, "whiskey"),
    BOURBON_WHISKY(R.string.bourbon_whisky_short, R.string.bourbon_whisky_long, "whiskey"),
    RYE_WHISKY(R.string.rye_whisky_short, R.string.rye_whisky_long, "whiskey"),
    IRISH_WHISKY(R.string.irish_whisky_short, R.string.irish_whisky_long, "whiskey"),

    // Wines
    RED_WINE(R.string.red_wine_short, R.string.red_wine_long),
    WHITE_WINE(R.string.white_wine_short, R.string.white_wine_long),
    SPARKLING_WINE(R.string.sparkling_wine_short, R.string.sparkling_wine_long, "champagne", "prosecco"),
    DRY_VERMOUTH(R.string.dry_vermouth_short, R.string.dry_vermouth_long),
    SWEET_RED_VERMOUTH(R.string.sweet_red_vermouth_short, R.string.sweet_red_vermouth_long),
    SWEET_WHITE_VERMOUTH(R.string.sweet_white_vermouth_short, R.string.sweet_white_vermouth_long),
    RED_PORT(R.string.red_port_short, R.string.red_port_long),
    WHITE_PORT(R.string.white_port_short, R.string.white_port_long),
    LILLET(R.string.lillet_short, R.string.lillet_long),

    COLA(R.string.cola_short, R.string.cola_long, "coca", "pepsi"),
    GINGER_ALE(R.string.ginger_ale_short, R.string.ginger_ale_long),
    GINGER_BEER(R.string.ginger_beer_short, R.string.ginger_beer_long),

    // Fruits & vegetables
    ORANGES(R.string.oranges_short, R.string.oranges_long),
    LEMONS(R.string.lemons_short, R.string.lemons_long),
    LIMES(R.string.limes_short, R.string.limes_long),
    PINEAPPLES(R.string.pineapples_short, R.string.pineapples_long),
    PEACHES(R.string.peaches_short, R.string.peaches_long),
    TOMATOES(R.string.tomatoes_short, R.string.tomatoes_long),
    CRANBERRIES(R.string.cranberries_short, R.string.cranberries_long),
    OLIVES(R.string.olives_short, R.string.olives_long),
    GRAPEFRUITS(R.string.grapefruits_short, R.string.grapefruits_long),
    ONIONS(R.string.onions_short, R.string.onions_long),
    MARASCHINO_CHERRIES(R.string.maraschino_cherries_short, R.string.maraschino_cherries_long),

    // Sugars/syrups
    SUGAR_SYRUP(R.string.sugar_syrup_short, R.string.sugar_syrup_long, "simple"),
    GOMME_SYRUP(R.string.gomme_syrup_short, R.string.gomme_syrup_long, "gum"),
    ORGEAT_SYRUP(R.string.orgeat_syrup_short, R.string.orgeat_syrup_long),
    STRAWBERRY_SYRUP(R.string.strawberry_syrup_short, R.string.strawberry_syrup_long),
    RASPBERRY_SYRUP(R.string.raspberry_syrup_short, R.string.raspberry_syrup_long),
    HONEY(R.string.honey_short, R.string.honey_long),
    AGAVE_NECTAR(R.string.agave_nectar_short, R.string.agave_nectar_long),
    GRENADINE(R.string.grenadine_short, R.string.grenadine_long),

    // Misc
    SODA_WATER(R.string.soda_water_short, R.string.soda_water_long, "sparkling", "fizzy", "carbonated"),
    ORANGE_FLOWER_WATER(R.string.orange_flower_water_short, R.string.orange_flower_water_long),
    EGGS(R.string.eggs_short, R.string.eggs_long),
    MILK(R.string.milk_short, R.string.milk_long),
    CREAM(R.string.cream_short, R.string.cream_long),
    VANILLA(R.string.vanilla_short, R.string.vanilla_long),
    MINT_LEAVES(R.string.mint_leaves_short, R.string.mint_leaves_long),
    CHILI_PEPPERS(R.string.chili_peppers_short, R.string.chili_peppers_long),
    WORCESTERSHIRE_SAUCE(R.string.worcestershire_sauce_short, R.string.worcestershire_sauce_long),
    TABASCO(R.string.tabasco_short, R.string.tabasco_long),
    SALT(R.string.salt_short, R.string.salt_long),
    CELERY_SALT(R.string.celery_salt_short, R.string.celery_salt_long),
    PEPPER(R.string.pepper_short, R.string.pepper_long),
    NUTMEG(R.string.nutmeg_short, R.string.nutmeg_long),
    ;

    private final int shortNameRes;
    private final int longNameRes;
    private final Set<String> keywords;

    Ingredient(int shortNameRes, int longNameRes, String... keywords) {
        this.shortNameRes = shortNameRes;
        this.longNameRes = longNameRes;
        this.keywords = new HashSet<>(keywords.length);
        Collections.addAll(this.keywords, keywords);
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public String getShortName(Context context) {
        return context.getString(shortNameRes);
    }

    public String getLongName(Context context) {
        return context.getString(longNameRes);
    }
}
