package com.example.n01661190_jaswinderims.sevice;

import com.example.n01661190_jaswinderims.Product;

import com.example.n01661190_jaswinderims.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

@Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){this.productRepository=productRepository;}

    public List<Product> getAllProducts(){return productRepository.findAll();}


    public Product addProduct(Product product){return productRepository.save(product);}

    public Optional<Product> updateProduct(Long id, Product updatedProduct){



        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            return productRepository.save(product);
        });}

    public boolean deleteProduct(Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }return  false;
    }
    public Product UpdateStock(Long id,int stock){
        return productRepository.findById(id).map(product -> {
            product.setStock(stock);
            return productRepository.save(product);
        }).orElse(null);
    }


}
