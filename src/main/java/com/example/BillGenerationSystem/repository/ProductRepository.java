package com.example.BillGenerationSystem.repository;

import com.example.BillGenerationSystem.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE product_info SET available_quantity = available_quantity - ?1 WHERE product_id = ?2", nativeQuery = true)
    void updateQuantity(int quantity, int pId);

    @Query(value = "SELECT available_quantity from product_info WHERE product_id = ?1", nativeQuery = true)
    int getQuantity(int pId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product_info SET available_quantity = available_quantity + ?1 WHERE product_id = ?2", nativeQuery = true)
    void addStock(int quantity, int pId);
}
