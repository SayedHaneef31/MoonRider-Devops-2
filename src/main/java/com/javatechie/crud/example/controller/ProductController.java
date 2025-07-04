package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

//    @GetMapping("/products/search")
//    public List<Product> searchProducts(@RequestParam String keyword) {
//        return service.searchProductsByKeyword(keyword);
//    }
    @GetMapping("/products/search")
    public ResponseEntity<?> searchProducts(@RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Keyword parameter is required and cannot be empty");
        }

        List<Product> results = service.searchProductsByKeyword(keyword);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found matching the keyword");
        }

        return ResponseEntity.ok(results);
    }
    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
