package com.example.demo0925.mapper;

import com.example.demo0925.domain.ItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // MyBatis 매퍼임을 나타내는 어노테이션
public interface ItemMapper {

    // 새로운 상품을 데이터베이스에 삽입 (CREATE)
    // DTO 객체를 파라미터로 받음
    void insertItem(ItemDTO item);

    // 모든 상품 목록을 데이터베이스에서 조회 (READ All)
    // ItemDTO 객체의 리스트를 반환
    List<ItemDTO> selectAllItems();

    // 특정 item_id를 가진 상품 하나를 데이터베이스에서 조회 (READ One)
    // item_id를 파라미터로 받고 ItemDTO 객체를 반환
    ItemDTO selectItemById(int itemId);

    // 기존 상품 정보를 데이터베이스에서 업데이트 (UPDATE)
    // 업데이트할 정보가 담긴 DTO 객체를 파라미터로 받음
    void updateItem(ItemDTO item);

    // 특정 item_id를 가진 상품을 데이터베이스에서 삭제 (DELETE)
    // 삭제할 item_id를 파라미터로 받음
    void deleteItem(int itemId);
}
