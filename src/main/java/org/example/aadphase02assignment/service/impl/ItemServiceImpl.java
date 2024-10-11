package org.example.aadphase02assignment.service.impl;

import org.example.aadphase02assignment.dto.ItemStatus;
import org.example.aadphase02assignment.dto.impl.ItemDTO;
import org.example.aadphase02assignment.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional

public class ItemServiceImpl implements ItemService {
    @Override
    public void saveItem(ItemDTO itemDTO) {

    }

    @Override
    public ItemStatus getItemById(String id) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return List.of();
    }

    @Override
    public void deleteItem(String id) {

    }

    @Override
    public void updateItem(ItemDTO itemDTO) {

    }
}
