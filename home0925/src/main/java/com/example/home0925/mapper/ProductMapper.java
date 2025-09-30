package com.example.home0925.mapper;

import com.example.home0925.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 상품 관련 MyBatis Mapper 인터페이스
 */
@Mapper
public interface ProductMapper {

    // 기본 기능만 남겨두고 나머지는 주석처리
    List<ProductDTO> selectAllProducts();
    int selectTotalProductCount();
    List<String> selectAllBrands();
    List<String> selectAllLocations();

    // 임시로 Map으로 변경
    List<Map<String, Object>> selectProductCountByBrand();
    List<Map<String, Object>> selectProductCountByLocation();

    // 나머지는 주석처리
    // List<ProductDTO> selectProductsByBrand(@Param("brand") String brand);
    // List<ProductDTO> selectProductsByLocation(@Param("location") String location);
    // List<ProductDTO> selectProductsByPriceRange(@Param("minPrice") Long minPrice, @Param("maxPrice") Long maxPrice);
    // List<ProductDTO> selectProductsByRating(@Param("minRating") Double minRating);
    // ProductDTO selectProductById(@Param("itemId") Long itemId);
    // List<ProductDTO> selectProductsByKeyword(@Param("keyword") String keyword);
    // List<ProductDTO> selectProductsWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    // CategoryCountDTO 내부 클래스도 주석처리하거나 삭제
}