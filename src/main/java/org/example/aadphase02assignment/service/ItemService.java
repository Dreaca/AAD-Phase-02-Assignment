package org.example.aadphase02assignment.service;

import org.example.aadphase02assignment.dto.ItemStatus;
import org.example.aadphase02assignment.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    ItemStatus getItemById(String id);
    List<ItemDTO> getAllItems();
    void deleteItem(String id);
    void updateItem(String itemCode,ItemDTO itemDTO);
    public List<String> findSuggestions(String query);
}
