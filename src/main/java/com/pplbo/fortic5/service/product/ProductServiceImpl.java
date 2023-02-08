package com.pplbo.fortic5.service.product;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent())
            return productOptional.get();

        throw new NoSuchElementException("Product ID not found");
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
