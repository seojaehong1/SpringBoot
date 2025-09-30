package com.example.project.service;

import com.example.project.domain.Product;
import com.example.project.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productMapper.selectAllProducts();
    }
    public Product getProductById(Long id) {
        return productMapper.selectProductById(id);
    }

    public void UpdateProduct(Product product) {
        productMapper.updateProduct(product);
    }
    public void deleteProductById(Long id) {
        productMapper.deleteProductById(id);
    }
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }
    public List<Product> searchProducts(String keyword) {
        return productMapper.searchProducts(keyword);
    }
}
