package com.pplbo.fortic5.model.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

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
}