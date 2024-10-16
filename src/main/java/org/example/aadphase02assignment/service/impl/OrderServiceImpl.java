package org.example.aadphase02assignment.service.impl;

import org.example.aadphase02assignment.controller.OrderController;
import org.example.aadphase02assignment.dao.CustomerDao;
import org.example.aadphase02assignment.dao.ItemDao;
import org.example.aadphase02assignment.dao.OrderDao;
import org.example.aadphase02assignment.dto.impl.CartItemDto;
import org.example.aadphase02assignment.dto.impl.ItemDTO;
import org.example.aadphase02assignment.dto.impl.OrderDTO;
import org.example.aadphase02assignment.dto.impl.OrderRequestDto;
import org.example.aadphase02assignment.entity.impl.CustomerEntity;
import org.example.aadphase02assignment.entity.impl.ItemEntity;
import org.example.aadphase02assignment.entity.impl.OrderEntity;
import org.example.aadphase02assignment.exceptions.CustomerNotFoundException;
import org.example.aadphase02assignment.exceptions.DataPersistException;
import org.example.aadphase02assignment.service.OrderService;
import org.example.aadphase02assignment.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@CrossOrigin

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private ItemDao itemDao;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional(rollbackFor = {Exception.class, DataPersistException.class})
    public void saveOrder(OrderRequestDto order) throws DataPersistException {
        List<CartItemDto> cartItems = order.getCartItems();
        ArrayList<ItemDTO> itemList = new ArrayList<>();

        try {
            CustomerEntity customer = customerDao.findById(order.getCustomerId()).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
            for (CartItemDto cartItem : cartItems) {
                String itemCode = cartItem.getItemCode();

                // Retrieve the item entity and update the stock
                ItemEntity item = itemDao.findById(itemCode).orElseThrow(() -> new DataPersistException("Item not found: " + itemCode));

                itemList.add(mapping.toItemDTO(item));
                // Decrease the item quantity based on the ordered quantity
                if (item.getQto() < cartItem.getQty()) {
                    throw new DataPersistException("Insufficient stock for item: " + itemCode);
                }
                item.setQto(item.getQto() - cartItem.getQty());
                itemDao.save(item);  // Save the updated item with decreased quantity
            }

            // Save the order
            OrderEntity savedOrder = orderDao.save(mapping.toOrderEntity(new OrderDTO(
                    order.getOrderId(),
                    mapping.toCustomerDTO(customer),
                    order.getDate(),
                    order.getTotal(),
                    order.getDiscount(),
                    order.getSubTotal(),
                    itemList
            )));

            if (savedOrder == null) {
                throw new DataPersistException("Could not save order");
            }

        } catch (DataPersistException e) {
            throw e;  // Rethrow to trigger rollback
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataPersistException("An error occurred while saving the order");
        }
    }


    @Override
    public List<OrderDTO> getOrders() {
        return List.of();
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        return null;
    }

    @Override
    public void deleteOrder(String orderId) {

    }

    @Override
    public void updateOrder(OrderDTO order, String orderId) {

    }
}
