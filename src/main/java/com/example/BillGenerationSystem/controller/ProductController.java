package com.example.BillGenerationSystem.controller;

import com.example.BillGenerationSystem.DTO.StockUpdateRequest;
import com.example.BillGenerationSystem.DTO.StockUpdateResponse;
import com.example.BillGenerationSystem.model.Product;
import com.example.BillGenerationSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("add-products")
    public String addProducts(@RequestBody List<Product> products) {
        productService.addProducts(products);
        return "products added to inventory successfully";
    }

    @PutMapping("update-stock")
    public StockUpdateResponse updateStock(@RequestBody StockUpdateRequest request) {
        return productService.addStock(request);
    }
}
