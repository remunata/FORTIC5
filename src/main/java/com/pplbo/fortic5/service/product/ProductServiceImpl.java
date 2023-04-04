package com.pplbo.fortic5.service.product;

import com.pplbo.fortic5.model.product.Kondisi;
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
                .brand("Secret")
                .stock(512)
                .rating(5)
                .kondisi(Kondisi.BARU)
                .imageExtension(request.getImage().getOriginalFilename()
                        .substring(request.getImage().getOriginalFilename().lastIndexOf(".")))
                .build();

        return productRepository.save(product);
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
}
