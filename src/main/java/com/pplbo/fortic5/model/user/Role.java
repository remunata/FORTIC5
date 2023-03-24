package com.pplbo.fortic5.model.user;

import java.awt.*;

public enum Role {
    CUSTOMER("Customer"),
    SELLER("Seller");

    private final String displayValue;

    Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
