package com.pplbo.fortic5.repository;

import com.pplbo.fortic5.model.product.Product;
import com.pplbo.fortic5.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findBySeller(User user);
}
