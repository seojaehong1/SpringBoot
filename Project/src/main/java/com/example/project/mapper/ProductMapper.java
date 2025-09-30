package com.example.project.mapper;

import com.example.project.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectAllProducts();
    Product selectProductById(Long id);
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(Long id);
    List<Product> searchProducts(String keyword);
}
