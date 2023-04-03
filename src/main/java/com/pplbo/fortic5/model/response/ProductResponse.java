package com.pplbo.fortic5.model.response;

import com.pplbo.fortic5.model.product.Category;
import com.pplbo.fortic5.model.product.Kondisi;
import com.pplbo.fortic5.model.product.Product;

import static com.pplbo.fortic5.utilities.NumberFormatter.getCurrencyFormat;

public class ProductResponse {

    private final Product product;
    private final String imageUrl;

    public ProductResponse(Product product, String imageUrl) {
        this.product = product;
        this.imageUrl = imageUrl;
    }

    public Product getProduct() {
        return product;
    }

    public String getImage() {
        return imageUrl;
    }

    public Integer getId() {
        return product.getId();
    }

    public String getName() {
        return product.getName();
    }

    public String getPrice() {
        return getCurrencyFormat(product.getPrice());
    }

    public String getDescription() {
        return product.getDescription();
    }

    public Integer getRating() {
        return product.getRating();
    }

    public Integer getStock() {
        return product.getStock();
    }

    public String getBrand() {
        return product.getBrand();
    }

    public Kondisi getKondisi() {
        return product.getKondisi();
    }

    public Category getCategory() {
        return product.getCategory();
    }
}
