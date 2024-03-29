package com.pplbo.fortic5.service.product;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.request.ProductRequest;
import com.pplbo.fortic5.model.user.User;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Integer id);

    Product save(ProductRequest request, User user);

    Product edit(ProductRequest request, Integer id);

    boolean deleteById(Integer id);

    void saveAll(List<Product> products);

    List<Product> findBySeller(User user);

    List<Product> searchByName(String productName);

    List<Product> searchByName(String productName, User seller);
}
