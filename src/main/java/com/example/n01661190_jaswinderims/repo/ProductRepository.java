package com.example.n01661190_jaswinderims.repo;

import com.example.n01661190_jaswinderims.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

