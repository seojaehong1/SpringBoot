package com.example.demo0925.service;

import com.example.demo0925.domain.ItemDTO;

import java.util.List;

public interface ItemService {
    // 상품 추가
    void addItem(ItemDTO itemDTO);

    // 상품 전체 조회
    List<ItemDTO> getAllItems();

    // 특정 상품 조회
    ItemDTO getItemById(int itemId);

    // 상품 정보 수정
    void updateItem(ItemDTO itemDTO);

    // 상품 삭제
    void deleteItem(int itemId);
}
