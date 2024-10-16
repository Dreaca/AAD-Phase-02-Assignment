package org.example.aadphase02assignment.service.impl;

import org.example.aadphase02assignment.customStatusCodes.SelectedItemCustomerOrderErrorStatusCodes;
import org.example.aadphase02assignment.dao.ItemDao;
import org.example.aadphase02assignment.dto.ItemStatus;
import org.example.aadphase02assignment.dto.impl.ItemDTO;
import org.example.aadphase02assignment.entity.impl.ItemEntity;
import org.example.aadphase02assignment.exceptions.ItemNotFoundException;
import org.example.aadphase02assignment.service.ItemService;
import org.example.aadphase02assignment.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        ItemEntity saved = itemDao.save(mapping.toItemEntity(itemDTO));
        if (saved == null) {
            throw new DataIntegrityViolationException("Item already exists");
        }
    }

    @Override
    public ItemStatus getItemById(String id) {
        if (id == null || id.isEmpty()) {
            throw new DataIntegrityViolationException("Item id cannot be empty");
        } else if (itemDao.existsById(id)) {
           return mapping.toItemDTO(itemDao.getById(id));
        }
        return new SelectedItemCustomerOrderErrorStatusCodes(2,"Item not found");
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mapping.toItemDTOList(itemDao.findAll());
    }

    @Override
    public void deleteItem(String id) {
        Optional<ItemEntity> itemEntity = itemDao.findById(id);
        if (id == null || id.isEmpty()) {
            throw new DataIntegrityViolationException("Item id cannot be empty");
        } else if (!itemEntity.isPresent()) {
            throw new ItemNotFoundException("Item with"+id+" not found");
        }else {
            itemDao.deleteById(id);
        }
    }

    @Override
    public void updateItem(String itemCode, ItemDTO itemDTO) {
        Optional<ItemEntity> itemEntity = itemDao.findById(itemCode);
        if (itemEntity.isPresent()) {
            itemEntity.get().setItemName(itemDTO.getItemName());
            itemEntity.get().setQto(itemDTO.getQto());
            itemEntity.get().setAuthor(itemDTO.getAuthor());
            itemEntity.get().setPrice(itemDTO.getPrice());
        }
    }
    @Override
    public List<String> findSuggestions(String query) {
        // Assuming that you have an ItemEntity class with 'id', 'name', 'price', and 'qty' properties
        List<ItemEntity> items = itemDao.findAll();

        // Filter items based on the query and return a formatted string for each item
        return items.stream()
                .filter(item -> item.getItemName().toLowerCase().contains(query.toLowerCase()))
                .map(item -> String.format(" Name: %s -Code: %s -Price: %s -QTO: %d", item.getItemName(), item.getItemCode(), item.getPrice(), item.getQto()))
                .collect(Collectors.toList());
    }


}
