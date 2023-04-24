package com.pplbo.fortic5.model.product;

public enum Kondisi {

    BARU("Baru"),
    BEKAS("Bekas");

    private final String displayValue;

    Kondisi(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
