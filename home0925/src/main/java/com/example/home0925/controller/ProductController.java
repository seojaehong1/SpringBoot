package com.example.home0925.controller;

import com.example.home0925.domain.ProductDTO;
import com.example.home0925.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 상품 관련 Controller
 */
@Slf4j
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 메인 상품 목록 페이지 (카테고리 포함)
     * @param model Thymeleaf 모델
     * @return 상품 목록 뷰
     */
    @GetMapping
    public String productList(Model model) {
        log.info("상품 목록 페이지 요청");

        // 카테고리 정보와 함께 상품 데이터 조회
        ProductService.ProductServiceResponse response = productService.getProductsWithCategories();

        model.addAttribute("products", response.getProducts());
        model.addAttribute("brands", response.getBrands());
        model.addAttribute("locations", response.getLocations());
        model.addAttribute("brandStats", response.getBrandStatistics());
        model.addAttribute("locationStats", response.getLocationStatistics());
        model.addAttribute("totalCount", response.getTotalCount());
        model.addAttribute("currentCategory", "전체");
        model.addAttribute("currentFilter", "all");

        return "products/list";
    }

    /**
     * 브랜드별 상품 조회
     * @param brand 브랜드명
     * @param model Thymeleaf 모델
     * @return 상품 목록 뷰
     */
    @GetMapping("/brand/{brand}")
    public String productsByBrand(@PathVariable String brand, Model model) {
        log.info("브랜드별 상품 조회: {}", brand);

        List<ProductDTO> products = productService.getProductsByBrand(brand);

        // 카테고리 정보도 함께 전달 (사이드바용)
        model.addAttribute("products", products);
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("locations", productService.getAllLocations());
        model.addAttribute("brandStats", productService.getBrandStatistics());
        model.addAttribute("locationStats", productService.getLocationStatistics());
        model.addAttribute("totalCount", products.size());
        model.addAttribute("currentCategory", brand);
        model.addAttribute("currentFilter", "brand");

        return "products/list";
    }

    /**
     * 지역별 상품 조회
     * @param location 지역명
     * @param model Thymeleaf 모델
     * @return 상품 목록 뷰
     */
    @GetMapping("/location/{location}")
    public String productsByLocation(@PathVariable String location, Model model) {
        log.info("지역별 상품 조회: {}", location);

        List<ProductDTO> products = productService.getProductsByLocation(location);

        model.addAttribute("products", products);
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("locations", productService.getAllLocations());
        model.addAttribute("brandStats", productService.getBrandStatistics());
        model.addAttribute("locationStats", productService.getLocationStatistics());
        model.addAttribute("totalCount", products.size());
        model.addAttribute("currentCategory", location);
        model.addAttribute("currentFilter", "location");

        return "products/list";
    }

    /**
     * 가격대별 상품 조회
     * @param min 최소 가격
     * @param max 최대 가격
     * @param model Thymeleaf 모델
     * @return 상품 목록 뷰
     */
    @GetMapping("/price")
    public String productsByPrice(@RequestParam(required = false) Long min,
                                  @RequestParam(required = false) Long max,
                                  Model model) {
        log.info("가격대별 상품 조회: {} ~ {}", min, max);

        List<ProductDTO> products = productService.getProductsByPriceRange(min, max);

        model.addAttribute("products", products);
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("locations", productService.getAllLocations());
        model.addAttribute("brandStats", productService.getBrandStatistics());
        model.addAttribute("locationStats", productService.getLocationStatistics());
        model.addAttribute("totalCount", products.size());
        model.addAttribute("currentCategory", min + "원 ~ " + max + "원");
        model.addAttribute("currentFilter", "price");
        model.addAttribute("minPrice", min);
        model.addAttribute("maxPrice", max);

        return "products/list";
    }

    /**
     * 평점별 상품 조회
     * @param rating 최소 평점
     * @param model Thymeleaf 모델
     * @return 상품 목록 뷰
     */
    @GetMapping("/rating")
    public String productsByRating(@RequestParam Double rating, Model model) {
        log.info("평점별 상품 조회: {} 이상", rating);

        List<ProductDTO> products = productService.getProductsByRating(rating);

        model.addAttribute("products", products);
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("locations", productService.getAllLocations());
        model.addAttribute("brandStats", productService.getBrandStatistics());
        model.addAttribute("locationStats", productService.getLocationStatistics());
        model.addAttribute("totalCount", products.size());
        model.addAttribute("currentCategory", rating + "점 이상");
        model.addAttribute("currentFilter", "rating");
        model.addAttribute("minRating", rating);

        return "products/list";
    }

    /**
     * 상품 검색
     * @param keyword 검색 키워드
     * @param model Thymeleaf 모델
     * @return 상품 목록 뷰
     */
    @GetMapping("/search")
    public String searchProducts(@RequestParam String keyword, Model model) {
        log.info("상품 검색: {}", keyword);

        List<ProductDTO> products = productService.searchProducts(keyword);

        model.addAttribute("products", products);
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("locations", productService.getAllLocations());
        model.addAttribute("brandStats", productService.getBrandStatistics());
        model.addAttribute("locationStats", productService.getLocationStatistics());
        model.addAttribute("totalCount", products.size());
        model.addAttribute("currentCategory", "검색: " + keyword);
        model.addAttribute("currentFilter", "search");
        model.addAttribute("keyword", keyword);

        return "products/list";
    }

    /**
     * 상품 상세 조회
     * @param itemId 상품 ID
     * @param model Thymeleaf 모델
     * @return 상품 상세 뷰
     */
    @GetMapping("/{itemId}")
    public String productDetail(@PathVariable Long itemId, Model model) {
        log.info("상품 상세 조회: {}", itemId);

        ProductDTO product = productService.getProductById(itemId);
        if (product == null) {
            log.warn("상품을 찾을 수 없음: {}", itemId);
            return "redirect:/products";
        }

        model.addAttribute("product", product);

        return "products/detail";
    }

    /**
     * AJAX용 브랜드별 상품 조회 API
     * @param brand 브랜드명
     * @return JSON 응답
     */
    @GetMapping("/api/brand/{brand}")
    @ResponseBody
    public List<ProductDTO> getProductsByBrandApi(@PathVariable String brand) {
        log.info("API - 브랜드별 상품 조회: {}", brand);
        return productService.getProductsByBrand(brand);
    }

    /**
     * AJAX용 지역별 상품 조회 API
     * @param location 지역명
     * @return JSON 응답
     */
    @GetMapping("/api/location/{location}")
    @ResponseBody
    public List<ProductDTO> getProductsByLocationApi(@PathVariable String location) {
        log.info("API - 지역별 상품 조회: {}", location);
        return productService.getProductsByLocation(location);
    }
}