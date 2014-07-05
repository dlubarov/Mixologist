package com.lubarov.daniel.mixologist;

/**
 * A recipe category.
 */
public enum Category {
    ABSINTHE("Absinthe"),
    BITTERS("Bitters"),
    BRANDY("Brandy"),
    CHOCOLATE("Chocolate"),
    COFFEE("Coffee"),
    FRUIT_LIQUEUR("Fruit Liqueur"),
    GIN("Gin"),
    RUM("Rum"),
    TEQUILA("Tequila"),
    VERMOUTH("Vermouth"),
    VODKA("Vodka"),
    WHISKY("Whisky"),
    WINE("Wine/Champagne"),
    ;

    private final String humanReadableName;

    Category(String humanReadableName) {
        this.humanReadableName = humanReadableName;
    }

    @Override
    public String toString() {
        return humanReadableName;
    }
}
