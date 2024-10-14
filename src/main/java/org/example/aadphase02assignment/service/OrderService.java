package org.example.aadphase02assignment.service;

import org.example.aadphase02assignment.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO order);
    List<OrderDTO> getOrders();
    OrderDTO getOrder(String orderId);
    void deleteOrder(String orderId);
    void updateOrder(OrderDTO order,String orderId);
}
