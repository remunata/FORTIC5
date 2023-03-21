package com.pplbo.fortic5.service.product;

import com.pplbo.fortic5.model.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);

    Product save(Product product);

    void saveAll(List<Product> products);
}
