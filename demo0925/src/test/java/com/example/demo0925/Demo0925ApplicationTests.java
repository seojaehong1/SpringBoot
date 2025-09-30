package com.example.demo0925;

import com.example.demo0925.domain.ItemDTO; // DTO 임포트
import com.example.demo0925.mapper.ItemMapper; // Mapper 임포트
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

@SpringBootTest
@Transactional
class Demo0925ApplicationTests {

    @Autowired // 스프링 컨텍스트에서 ItemMapper 빈을 주입받음
    private ItemMapper itemMapper;

    // --- CRUD 테스트 메서드 ---

    // 1. 상품 삽입 (CREATE) 및 단일 조회 (READ One) 테스트
    @Test
    void testInsertAndSelect() {
        // 1. DTO 객체 생성 및 값 설정
        ItemDTO newItem = new ItemDTO();
        newItem.setTitle("테스트 상품");
        newItem.setBrand("테스트 브랜드");
        newItem.setDescription("테스트 상품 설명입니다.");
        newItem.setPrice(99);

        // 2. 상품 등록 (INSERT)
        itemMapper.insertItem(newItem);
        int newId = newItem.getItemId();

        // --- 삽입된 데이터 정보 출력 ---
        System.out.println("✅ 새로운 상품이 삽입되었습니다.");
        System.out.println("-> ID: " + newId);
        System.out.println("-> 제목: " + newItem.getTitle());

        // 3. 등록된 상품 조회 (READ One)
        ItemDTO foundItem = itemMapper.selectItemById(newId);

        // --- 조회된 데이터 정보 출력 ---
        System.out.println("\n🔍 ID " + newId + "번 상품을 조회했습니다.");
        System.out.println("-> 제목: " + foundItem.getTitle());
        System.out.println("-> 브랜드: " + foundItem.getBrand());
        System.out.println("-> 가격: " + foundItem.getPrice());

        // 4. 조회 결과 검증
        Assertions.assertNotNull(foundItem, "등록된 상품이 조회되지 않습니다.");
        Assertions.assertEquals("테스트 상품", foundItem.getTitle(), "조회된 상품의 제목이 일치하지 않습니다.");
        // ... 나머지 검증 코드
    }

    // 2. 모든 상품 목록 조회 (READ All) 테스트
    @Test
    void testSelectAllItems() {
        // 테스트 데이터 삽입 (테스트 격리를 위해 여기서 삽입)
        itemMapper.insertItem(createTestItem("상품1", "브랜드A", 100));
        itemMapper.insertItem(createTestItem("상품2", "브랜드B", 200));

        // --- 데이터 삽입 확인 출력 ---
        System.out.println("✅ 테스트 데이터 2개가 삽입되었습니다.");

        // 모든 상품 조회
        List<ItemDTO> allItems = itemMapper.selectAllItems();

        // --- 조회 결과 출력 ---
        System.out.println("\n🔍 전체 상품 목록을 조회합니다. (총 " + allItems.size() + "개)");

        for (ItemDTO item : allItems) {
            System.out.println("------------------------------------");
            System.out.println("-> ID: " + item.getItemId());
            System.out.println("-> 제목: " + item.getTitle());
            System.out.println("-> 브랜드: " + item.getBrand());
            System.out.println("-> 가격: " + item.getPrice());
        }
        System.out.println("------------------------------------");

        // 조회 결과 검증
        Assertions.assertNotNull(allItems, "전체 상품 목록이 null입니다.");
        Assertions.assertFalse(allItems.isEmpty(), "전체 상품 목록이 비어있으면 안 됩니다.");
        Assertions.assertTrue(allItems.size() >= 2, "최소 2개 이상의 상품이 조회되어야 합니다.");
    }

    // 3. 상품 정보 수정 (UPDATE) 테스트
    @Test
    void testUpdateItem() {
        // 1. 테스트 상품 등록
        ItemDTO itemToUpdate = createTestItem("수정 전 상품", "오래된 브랜드", 50);
        itemMapper.insertItem(itemToUpdate);
        int itemId = itemToUpdate.getItemId();

        // --- Before Update: Print the initial item details ---
        System.out.println("✅ Original item created.");
        System.out.println("-> ID: " + itemId);
        System.out.println("-> Title: " + itemToUpdate.getTitle());
        System.out.println("-> Brand: " + itemToUpdate.getBrand());
        System.out.println("-> Price: " + itemToUpdate.getPrice());
        System.out.println("------------------------------------");

        // 2. 상품 정보 수정
        itemToUpdate.setTitle("수정 후 상품");
        itemToUpdate.setBrand("새로운 브랜드");
        itemToUpdate.setPrice(75);
        itemMapper.updateItem(itemToUpdate);

        // 3. 수정된 상품 조회
        ItemDTO updatedItem = itemMapper.selectItemById(itemId);

        // --- After Update: Print the updated item details from the database ---
        System.out.println("\n🔍 Item with ID " + itemId + " has been updated and retrieved.");
        System.out.println("-> New Title: " + updatedItem.getTitle());
        System.out.println("-> New Brand: " + updatedItem.getBrand());
        System.out.println("-> New Price: " + updatedItem.getPrice());
        System.out.println("------------------------------------");

        // 4. 수정 결과 검증
        Assertions.assertNotNull(updatedItem, "Updated item should not be null.");
        Assertions.assertEquals("수정 후 상품", updatedItem.getTitle(), "Title update failed.");
        Assertions.assertEquals("새로운 브랜드", updatedItem.getBrand(), "Brand update failed.");
        Assertions.assertEquals(75, updatedItem.getPrice(), "Price update failed.");
    }

    // 4. 상품 삭제 (DELETE) 테스트
    @Test
    void testDeleteItem() {
        // 1. 테스트 상품 등록
        ItemDTO itemToDelete = createTestItem("삭제될 상품", "브랜드X", 10);
        itemMapper.insertItem(itemToDelete);
        int itemId = itemToDelete.getItemId();

        // --- Before Deletion: Print the item details that will be deleted ---
        System.out.println("✅ Item created for deletion.");
        System.out.println("-> ID: " + itemId);
        System.out.println("-> Title: " + itemToDelete.getTitle());
        System.out.println("------------------------------------");

        // 2. 상품 삭제
        itemMapper.deleteItem(itemId);

        // --- Deletion Confirmation ---
        System.out.println("\n🗑️ Item with ID " + itemId + " has been deleted.");

        // 3. 삭제 후 조회, 결과가 null인지 확인
        ItemDTO deletedItem = itemMapper.selectItemById(itemId);

        // --- After Deletion: Print the result of the select operation ---
        System.out.println("🔍 Attempting to retrieve the deleted item...");
        if (deletedItem == null) {
            System.out.println("-> Result: The item was not found. (Expected behavior)");
        } else {
            System.out.println("-> Result: The item was found unexpectedly.");
        }
        System.out.println("------------------------------------");

        // 4. 검증: 결과가 null인지 확인
        Assertions.assertNull(deletedItem, "상품이 성공적으로 삭제되지 않았습니다.");
    }


    // 테스트용 ItemDTO 객체를 편리하게 생성하기 위한 헬퍼 메서드
    private ItemDTO createTestItem(String title, String brand, int price) {
        ItemDTO dto = new ItemDTO();
        dto.setTitle(title);
        dto.setBrand(brand);
        dto.setDescription("자동 생성된 테스트 설명");
        dto.setPrice(price);
        return dto;
    }
}