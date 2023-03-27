package com.pplbo.fortic5.model.response;

import com.pplbo.fortic5.model.product.Category;
import com.pplbo.fortic5.model.product.Kondisi;
import com.pplbo.fortic5.model.product.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.pplbo.fortic5.utilities.NumberFormatter.getCurrencyFormat;

public class ProductResponse {

    private static final String IMAGE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/static/assets/products/";

    private final Product product;
    private List<Path> paths;

    public ProductResponse(Product product) {
        this.product = product;

        try (Stream<Path> pathStream = Files.walk(Paths.get(IMAGE_PATH + product.getId()))) {
            paths = pathStream
                    .filter(Files::isRegularFile)
                    .toList();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Product getProduct() {
        return product;
    }

    public String getMainImage() {
        return paths.get(0).toString().substring(48);
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

    public Integer getTotalOrders() {
        return product.getOrders().size();
    }
}
