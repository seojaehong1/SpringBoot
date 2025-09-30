package com.example.home0925.domain;

import lombok.Data;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

/**
 * 상품 정보를 담는 DTO 클래스
 * 3개 테이블(products, product_details, product_reviews)의 데이터를 조합
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    // products 테이블 필드
    private Long itemId;
    private Long shopId;
    private String shopLocation;
    private String name;

    // product_details 테이블 필드
    private String brand;
    private Integer soldQuantity;
    private Integer stock;
    private Long discountPrice;
    private Long originalPrice;
    private String discount;
    private Integer likedCount;
    private BigDecimal ratingStar;
    private Integer numberOfRatings;
    private String productImageLink;

    // 추가 정보 (계산된 값들)
    private Integer reviewCount;  // 해당 상품의 리뷰 개수

    // 베트남 동(VND) 가격 포맷팅 메서드
    public String getFormattedDiscountPrice() {
        if (discountPrice == null) return "0 VND";
        return String.format("%,d VND", discountPrice);
    }

    public String getFormattedOriginalPrice() {
        if (originalPrice == null) return "0 VND";
        return String.format("%,d VND", originalPrice);
    }

    // 평점 소수점 2자리로 포맷팅
    public String getFormattedRatingStar() {
        if (ratingStar == null) return "0.00";
        return String.format("%.2f", ratingStar);
    }

    // 재고 상태 확인
    public boolean isInStock() {
        return stock != null && stock > 0;
    }

    // 할인 여부 확인
    public boolean hasDiscount() {
        return discount != null && !discount.equals("0%");
    }
}