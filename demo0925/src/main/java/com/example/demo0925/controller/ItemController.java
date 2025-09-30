package com.example.demo0925.controller;

import com.example.demo0925.domain.ItemDTO;
import com.example.demo0925.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 상품 목록 페이지
    @GetMapping
    public String getAllItems(Model model) {
        List<ItemDTO> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "list";
    }

    // --- 상품 등록 ---

    // 상품 등록 폼 페이지를 보여줌
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("item", new ItemDTO());
        return "add";
    }

    // 상품 등록 폼 제출 처리
    @PostMapping("/add")
    public String addItem(@ModelAttribute ItemDTO itemDTO, RedirectAttributes redirectAttributes) {
        itemService.addItem(itemDTO);
        redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 등록되었습니다.");
        return "redirect:/items";
    }

    // --- 상품 상세 보기 ---

    // 특정 상품의 상세 페이지를 보여줌
    @GetMapping("/detail/{id}")
    public String showItemDetail(@PathVariable("id") int itemId, Model model) {
        ItemDTO item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "detail";
    }

    // --- 상품 수정 ---

    // 상품 수정 폼 페이지를 보여줌
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int itemId, Model model) {
        ItemDTO item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "edit";
    }

    // 상품 수정 폼 제출 처리
    @PostMapping("/edit")
    public String updateItem(@ModelAttribute ItemDTO itemDTO, RedirectAttributes redirectAttributes) {
        itemService.updateItem(itemDTO);
        redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 수정되었습니다.");
        return "redirect:/items/detail/" + itemDTO.getItemId();
    }

    // --- 상품 삭제 ---

    // 상품 삭제 처리
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") int itemId, RedirectAttributes redirectAttributes) {
        itemService.deleteItem(itemId);
        redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 삭제되었습니다.");
        return "redirect:/items";
    }


}
