/*
 * Copyright 2024 Buddhika Pathum
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
import org.example.aadphase02assignment.entity.impl.OrderItemEntity;
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


        try {
            CustomerEntity customer = customerDao.findById(order.getCustomerId()).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));

            OrderEntity newOrder = mapping.toOrderEntity(new OrderDTO(
                    order.getOrderId(),
                    mapping.toCustomerDTO(customer),
                    order.getDate(),
                    order.getTotal(),
                    order.getDiscount(),
                    order.getSubTotal(),
                    new ArrayList<>()
            ));

            for (CartItemDto cartItem : cartItems) {
                String itemCode = cartItem.getItemCode();

                // Retrieve the item entity and update the stock
                ItemEntity item = itemDao.findById(itemCode).orElseThrow(() -> new DataPersistException("Item not found: " + itemCode));

                // Decrease the item quantity based on the ordered quantity
                if (item.getQto() < cartItem.getQty()) {
                    throw new DataPersistException("Insufficient stock for item: " + itemCode);
                }
                item.setQto(item.getQto() - cartItem.getQty());
                itemDao.save(item);  // Save the updated item with decreased quantity

                // Create an OrderItemEntity for each item in the cart
                var orderItem = new OrderItemEntity();
                orderItem.setOrder(newOrder);
                orderItem.setItem(item);
                orderItem.setQuantity(cartItem.getQty());
                newOrder.getOrderItems().add(orderItem); // Add the OrderItemEntity to the order
            }

            // Save the order
            OrderEntity savedOrder = orderDao.save(newOrder);

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
        List<OrderEntity> all = orderDao.findAll();
        return mapping.toOrderDTOList(all);
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
