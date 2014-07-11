package com.lubarov.daniel.mixologist.quantities;

public final class Mass {
    private final double kilograms;

    public static Mass fromKilograms(double kilograms) {
        return new Mass(kilograms);
    }

    public static Mass fromOunces(double ounces) {
        return new Mass(ounces / 35.274);
    }

    private Mass(double kilograms) {
        this.kilograms = kilograms;
    }

    public double getKilograms() {
        return kilograms;
    }

    public double getGrams() {
        return kilograms * 1000;
    }

    public double getOunces() {
        return kilograms * 35.274;
    }
}
