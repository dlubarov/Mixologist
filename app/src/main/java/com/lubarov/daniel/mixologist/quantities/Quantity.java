package com.lubarov.daniel.mixologist.quantities;

/**
 * A quantity of liquid. This represents either volume or mass. When converting between them, we use the density of
 * water, per convention, even though ethanol and other liquids have different densities.
 */
public class Quantity {
    private final double litres;

    public static Quantity from(double amount, Unit unit) {
        switch (unit) {
            case CL:
                return fromCentilitres(amount);
            case ML:
                return fromMillilitres(amount);
            case OZ:
                return fromOunces(amount);
            default:
                throw new IllegalArgumentException("Unexpected unit: " + unit);
        }
    }

    public static Quantity fromCentilitres(double centilitres) {
        return new Quantity(centilitres / 100);
    }

    public static Quantity fromMillilitres(double millilitres) {
        return new Quantity(millilitres / 1000);
    }

    public static Quantity fromOunces(double ounces) {
        return new Quantity(ounces / 33.814);
    }

    private Quantity(double litres) {
        this.litres = litres;
    }

    public double get(Unit unit) {
        switch (unit) {
            case CL:
                return getCentilitres();
            case ML:
                return getMillilitres();
            case OZ:
                return getOunces();
            default:
                throw new IllegalArgumentException("Unexpected unit: " + unit);
        }
    }

    public double getCentilitres() {
        return litres * 100;
    }

    public double getMillilitres() {
        return litres * 1000;
    }

    public double getOunces() {
        return litres * 33.814;
    }
}
