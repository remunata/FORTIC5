package com.pplbo.fortic5.utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberFormatter {

    private static final DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setGroupingSeparator('.');
        decimalFormat = new DecimalFormat("###,###", formatSymbols);
    }

    public static String getCurrencyFormat(double price) {
        return "Rp " + decimalFormat.format(price);
    }
}
