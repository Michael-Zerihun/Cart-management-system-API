package org.cartManagement.service;

import org.cartManagement.entity.Product;
import org.cartManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addMultipleProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }
}
