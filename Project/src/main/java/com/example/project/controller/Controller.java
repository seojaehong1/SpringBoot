package com.example.project.controller;

import com.example.project.domain.Product;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {

    private final ProductService productService; // 여기에 final 키워드 추가 👈



    @GetMapping("/")
    public String index(@RequestParam(required = false) String keyword, Model model) {
        List<Product> products;
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productService.searchProducts(keyword);
        } else {
            products = productService.getAllProducts();
        }
        model.addAttribute("product", products);
        return "index";
    }


    @GetMapping("/add-product")
    public String showAddProductForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            // 수정 모드: ID를 사용하여 상품 정보를 가져와 폼에 전달
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
        } else {
            // 추가 모드: 빈 상품 객체를 폼에 전달
            model.addAttribute("product", new Product());
        }
        return "add-product";
    }

    // UPSERT(추가 및 수정) 처리를 담당하는 메서드
    @PostMapping("/upsert-product")
    public String upsertProduct(Product product) {
        if (product.getId() != null) {
            // ID가 있으면 수정
            productService.UpdateProduct(product);
        } else {
            // ID가 없으면 추가
            productService.insertProduct(product);
        }
        return "redirect:/";
    }

    @PostMapping("/add-product")
    public String addProduct(Product product) {
        productService.insertProduct(product);
        return "redirect:/";
    }
    @PostMapping("/delete-product") // 삭제 요청을 처리하는 메서드 추가
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/";
    }
}