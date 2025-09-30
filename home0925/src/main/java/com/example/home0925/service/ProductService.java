package com.example.home0925.service;

import com.example.home0925.domain.ProductDTO;

import java.util.List;
import java.util.Map;


/**
 * 상품 관련 Service 인터페이스
 */
public interface ProductService {

    /**
     * 모든 상품 조회
     * @return 전체 상품 목록
     */
    List<ProductDTO> getAllProducts();

    /**
     * 브랜드별 상품 조회 (임시로 전체 상품 반환)
     * @param brand 브랜드명
     * @return 해당 브랜드 상품 목록
     */
    List<ProductDTO> getProductsByBrand(String brand);

    /**
     * 지역별 상품 조회 (임시로 전체 상품 반환)
     * @param location 지역명
     * @return 해당 지역 상품 목록
     */
    List<ProductDTO> getProductsByLocation(String location);

    /**
     * 가격대별 상품 조회 (임시로 전체 상품 반환)
     * @param minPrice 최소 가격
     * @param maxPrice 최대 가격
     * @return 해당 가격대 상품 목록
     */
    List<ProductDTO> getProductsByPriceRange(Long minPrice, Long maxPrice);

    /**
     * 평점별 상품 조회 (임시로 전체 상품 반환)
     * @param minRating 최소 평점
     * @return 해당 평점 이상 상품 목록
     */
    List<ProductDTO> getProductsByRating(Double minRating);

    /**
     * 상품 검색 (임시로 전체 상품 반환)
     * @param keyword 검색 키워드
     * @return 검색 결과 상품 목록
     */
    List<ProductDTO> searchProducts(String keyword);

    /**
     * 특정 상품 상세 조회 (임시로 첫 번째 상품 반환)
     * @param itemId 상품 ID
     * @return 상품 상세 정보
     */
    ProductDTO getProductById(Long itemId);

    /**
     * 페이징 처리된 상품 목록 조회
     * @param page 페이지 번호 (1부터 시작)
     * @param size 페이지 크기
     * @return 페이징된 상품 목록
     */
    List<ProductDTO> getProductsWithPaging(int page, int size);

    /**
     * 전체 상품 개수 조회
     * @return 전체 상품 개수
     */
    int getTotalProductCount();

    /**
     * 모든 브랜드 목록 조회
     * @return 브랜드 목록
     */
    List<String> getAllBrands();

    /**
     * 모든 지역 목록 조회
     * @return 지역 목록
     */
    List<String> getAllLocations();

    /**
     * 브랜드별 상품 개수 통계
     * @return 브랜드별 상품 개수 맵
     */
    Map<String, Long> getBrandStatistics();

    /**
     * 지역별 상품 개수 통계
     * @return 지역별 상품 개수 맵
     */
    Map<String, Long> getLocationStatistics();

    /**
     * 카테고리 정보와 함께 상품 조회 (메인 페이지용)
     * @return 카테고리 정보가 포함된 상품 데이터
     */
    ProductServiceResponse getProductsWithCategories();

    /**
     * Service 응답 데이터를 담는 클래스
     */
    class ProductServiceResponse {
        private final List<ProductDTO> products;
        private final List<String> brands;
        private final List<String> locations;
        private final Map<String, Long> brandStatistics;
        private final Map<String, Long> locationStatistics;
        private final int totalCount;

        public ProductServiceResponse(List<ProductDTO> products, List<String> brands,
                                      List<String> locations, Map<String, Long> brandStatistics,
                                      Map<String, Long> locationStatistics, int totalCount) {
            this.products = products;
            this.brands = brands;
            this.locations = locations;
            this.brandStatistics = brandStatistics;
            this.locationStatistics = locationStatistics;
            this.totalCount = totalCount;
        }

        // Getters
        public List<ProductDTO> getProducts() { return products; }
        public List<String> getBrands() { return brands; }
        public List<String> getLocations() { return locations; }
        public Map<String, Long> getBrandStatistics() { return brandStatistics; }
        public Map<String, Long> getLocationStatistics() { return locationStatistics; }
        public int getTotalCount() { return totalCount; }
    }
}