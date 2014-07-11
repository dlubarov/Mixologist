package com.lubarov.daniel.mixologist.quantities;

public final class Volume {
    private final double litres;

    public static Volume fromCentilitres(double centilitres) {
        return new Volume(centilitres / 100);
    }

    public static Volume fromMillilitres(double millilitres) {
        return new Volume(millilitres / 1000);
    }

    private Volume(double litres) {
        this.litres = litres;
    }

    public double getCentilitres() {
        return litres * 100;
    }

    public double getMillilitres() {
        return litres * 1000;
    }
}
