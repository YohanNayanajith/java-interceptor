package com.example.interceptor.controller;

import com.example.interceptor.model.Product;
import com.example.interceptor.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        LOG.info("Get all products");
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping("/")
    public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
        LOG.info("Product created");
        return ResponseEntity.ok().body(productService.saveProduct(product));
    }
}
