package com.pplbo.fortic5.service.product;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.request.ProductRequest;
import com.pplbo.fortic5.model.user.User;
import com.pplbo.fortic5.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Override
    public Product save(ProductRequest request, User user) {
        var product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .seller(user)
                .brand(request.getBrand())
                .stock(request.getStock())
                .rating(5)
                .kondisi(request.getKondisi())
                .imageExtension(request.getImage().getOriginalFilename()
                        .substring(request.getImage().getOriginalFilename().lastIndexOf(".")))
                .build();

        return productRepository.save(product);
    }

    @Override
    public Product edit(ProductRequest request, Integer id) {
        var product = findById(id);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setKondisi(request.getKondisi());
        product.setStock(request.getStock());

        return productRepository.save(product);
    }

    @Override
    public boolean deleteById(Integer id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public List<Product> findBySeller(User user) {
        return productRepository.findBySeller(user);
    }

    @Override
    public List<Product> searchByName(String productName) {
        return productRepository.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(productName.toLowerCase()))
                .toList();
    }

    @Override
    public List<Product> searchByName(String productName, User seller) {
        return productRepository.findBySeller(seller).stream()
                .filter(product -> product.getName().toLowerCase().contains(productName.toLowerCase()))
                .toList();
    }
}
