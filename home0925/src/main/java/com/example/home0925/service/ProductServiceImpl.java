package com.example.home0925.service;

import com.example.home0925.domain.ProductDTO;
import com.example.home0925.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ProductService 구현체
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("전체 상품 목록 조회");
        return productMapper.selectAllProducts();
    }

    @Override
    public List<ProductDTO> getProductsByBrand(String brand) {
        log.info("브랜드별 상품 조회: {}", brand);
        // 임시로 전체 상품 반환
        return getAllProducts();
    }

    @Override
    public List<ProductDTO> getProductsByLocation(String location) {
        log.info("지역별 상품 조회: {}", location);
        // 임시로 전체 상품 반환
        return getAllProducts();
    }

    @Override
    public List<ProductDTO> getProductsByPriceRange(Long minPrice, Long maxPrice) {
        log.info("가격대별 상품 조회: {} ~ {}", minPrice, maxPrice);
        // 임시로 전체 상품 반환
        return getAllProducts();
    }

    @Override
    public List<ProductDTO> getProductsByRating(Double minRating) {
        log.info("평점별 상품 조회: {} 이상", minRating);
        // 임시로 전체 상품 반환
        return getAllProducts();
    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        log.info("상품 검색: {}", keyword);
        // 임시로 전체 상품 반환
        return getAllProducts();
    }

    @Override
    public ProductDTO getProductById(Long itemId) {
        log.info("상품 상세 조회: {}", itemId);
        // 임시로 첫 번째 상품 반환
        List<ProductDTO> products = getAllProducts();
        return products.isEmpty() ? null : products.get(0);
    }

    @Override
    public List<ProductDTO> getProductsWithPaging(int page, int size) {
        log.info("페이징 상품 조회: page={}, size={}", page, size);
        // 임시로 전체 상품을 페이징하여 반환
        List<ProductDTO> allProducts = getAllProducts();
        int start = (page - 1) * size;
        int end = Math.min(start + size, allProducts.size());

        if (start >= allProducts.size()) {
            return List.of();
        }

        return allProducts.subList(start, end);
    }

    @Override
    public int getTotalProductCount() {
        return productMapper.selectTotalProductCount();
    }

    @Override
    public List<String> getAllBrands() {
        log.info("전체 브랜드 목록 조회");
        return productMapper.selectAllBrands();
    }

    @Override
    public List<String> getAllLocations() {
        log.info("전체 지역 목록 조회");
        return productMapper.selectAllLocations();
    }

    @Override
    public Map<String, Long> getBrandStatistics() {
        log.info("브랜드별 통계 조회");
        try {
            List<Map<String, Object>> results = productMapper.selectProductCountByBrand();
            return results.stream()
                    .collect(Collectors.toMap(
                            map -> (String) map.get("category"),
                            map -> ((Number) map.get("count")).longValue()
                    ));
        } catch (Exception e) {
            log.error("브랜드별 통계 조회 오류", e);
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, Long> getLocationStatistics() {
        log.info("지역별 통계 조회");
        try {
            List<Map<String, Object>> results = productMapper.selectProductCountByLocation();
            return results.stream()
                    .collect(Collectors.toMap(
                            map -> (String) map.get("category"),
                            map -> ((Number) map.get("count")).longValue()
                    ));
        } catch (Exception e) {
            log.error("지역별 통계 조회 오류", e);
            return new HashMap<>();
        }
    }

    @Override
    public ProductService.ProductServiceResponse getProductsWithCategories() {
        log.info("카테고리 정보와 함께 상품 조회");

        List<ProductDTO> products = getAllProducts();
        List<String> brands = getAllBrands();
        List<String> locations = getAllLocations();
        Map<String, Long> brandStats = getBrandStatistics();
        Map<String, Long> locationStats = getLocationStatistics();
        int totalCount = getTotalProductCount();

        return new ProductService.ProductServiceResponse(
                products, brands, locations, brandStats, locationStats, totalCount
        );
    }
}
