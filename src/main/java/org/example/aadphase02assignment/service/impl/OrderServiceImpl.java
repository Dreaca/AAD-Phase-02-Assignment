package org.example.aadphase02assignment.service.impl;

import org.example.aadphase02assignment.dto.impl.OrderDTO;
import org.example.aadphase02assignment.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional

public class OrderServiceImpl implements OrderService {
    @Override
    public void saveOrder(OrderDTO order) {

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
