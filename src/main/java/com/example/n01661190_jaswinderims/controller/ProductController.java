package com.example.n01661190_jaswinderims.controller;

import com.example.n01661190_jaswinderims.Product;
import com.example.n01661190_jaswinderims.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){return productService.getAllProducts();}

    @PostMapping
    public Product addProduct(@RequestBody Product product){return productService.addProduct(product);}

    @PutMapping("/{id}")
    public Optional<Product>  updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable Long id){return productService.deleteProduct(id);}

    @PutMapping("/stock/{id}")
    public Product UpdateStock(@PathVariable Long id,@RequestBody int stock){return productService.UpdateStock(id,stock);}



}
