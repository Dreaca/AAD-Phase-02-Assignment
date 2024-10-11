package org.example.aadphase02assignment.service;

import org.example.aadphase02assignment.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    ItemDTO getItemById(String id);
    List<ItemDTO> getAllItems();
    void deleteItem(String id);
    void updateItem(ItemDTO itemDTO);
}
