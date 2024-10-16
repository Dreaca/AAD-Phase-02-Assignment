package org.example.aadphase02assignment.controller;

import org.example.aadphase02assignment.dto.impl.OrderDTO;
import org.example.aadphase02assignment.dto.impl.OrderRequestDto;
import org.example.aadphase02assignment.service.CustomerService;
import org.example.aadphase02assignment.service.ItemService;
import org.example.aadphase02assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v3/orderdetail")
public class OrderDetailController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderRequestDto> getOrders(){
        List<OrderDTO> orders = orderService.getOrders();
        System.out.println(orders.toString());
        List<OrderRequestDto> dtos = new ArrayList<OrderRequestDto>();
        orders.forEach(order -> {
            OrderRequestDto dto = new OrderRequestDto();
            dto.setOrderId(order.getOrderId());
            dto.setCustomerId(order.getCustomer().getCustomerId());
            dto.setCustomerName(order.getCustomer().getCustomerName());
            dto.setDate(order.getDate());
            dto.setTotal(order.getTotal());
            dto.setDiscount(order.getDiscount());
            dto.setSubTotal(order.getSubtotal());
            dto.setCartItems(null);
            dtos.add(dto);
        });
        return dtos;
    }
}
