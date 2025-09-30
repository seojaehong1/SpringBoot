package com.example.demo0925.service.impl;

import com.example.demo0925.domain.ItemDTO;
import com.example.demo0925.mapper.ItemMapper;
import com.example.demo0925.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void addItem(ItemDTO itemDTO) {
        // 2. 구현 로직: 매퍼의 insertItem 메서드를 호출
        itemMapper.insertItem(itemDTO);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        // 2. 구현 로직: 매퍼의 selectAllItems 메서드를 호출
        return itemMapper.selectAllItems();
    }

    @Override
    public ItemDTO getItemById(int itemId) {
        // 2. 구현 로직: 매퍼의 selectItemById 메서드를 호출
        return itemMapper.selectItemById(itemId);
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        // 2. 구현 로직: 매퍼의 updateItem 메서드를 호출
        itemMapper.updateItem(itemDTO);
    }

    @Override
    public void deleteItem(int itemId) {
        // 2. 구현 로직: 매퍼의 deleteItem 메서드를 호출
        itemMapper.deleteItem(itemId);
    }
}
