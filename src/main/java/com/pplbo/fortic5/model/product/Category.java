package com.pplbo.fortic5.model.product;

public enum Category {

    LAPTOP("Laptop"),
    BUKU("Buku"),
    AKSESORIS("Aksesoris");

    private final String displayValue;

    Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
