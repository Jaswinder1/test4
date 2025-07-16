package com.example.n01661190_jaswinderims.controller;

import com.example.n01661190_jaswinderims.Product;
import com.example.n01661190_jaswinderims.sevice.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // DISPLAY ALL COFFEES (LIST PAGE)
    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";  // Return view (product-list.html)
    }

    // DISPLAY FORM TO ADD A NEW COFFEE
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-add";  // Return view (product-add.html)
    }

    // ADD A NEW COFFEE (FORM SUBMISSION)
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";  // Redirect to list after adding
    }

    // VIEW DETAILS OF A COFFEE
    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(product -> model.addAttribute("product", product));
        return "product-details";  // Return view (product-details.html)
    }

    // SHOW UPDATE FORM
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            return "product-edit";  // Return edit view
        } else {
            return "redirect:/products";  // Redirect if product not found
        }
    }

    // HANDLE UPDATE FORM SUBMISSION USING PUT
    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";  // Redirect to the product list after updating
    }

    // DELETE A COFFEE ITEM
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";  // Redirect to list after deletion
    }
}
