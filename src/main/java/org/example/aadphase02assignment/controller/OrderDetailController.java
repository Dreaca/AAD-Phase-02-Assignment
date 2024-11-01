/*
 * Copyright 2024 Buddhika Pathum
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.example.aadphase02assignment.controller;

import org.example.aadphase02assignment.dto.impl.CartItemDto;
import org.example.aadphase02assignment.dto.impl.ItemDTO;
import org.example.aadphase02assignment.dto.impl.OrderDTO;
import org.example.aadphase02assignment.dto.impl.OrderRequestDto;
import org.example.aadphase02assignment.entity.impl.ItemEntity;
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
    public List<OrderRequestDto> getOrders() {
        List<OrderDTO> orders = orderService.getOrders();
        System.out.println(orders.toString());
        List<OrderRequestDto> dtos = new ArrayList<>();

        orders.forEach(order -> {
            OrderRequestDto dto = new OrderRequestDto();
            dto.setOrderId(order.getOrderId());
            dto.setCustomerId(order.getCustomer().getCustomerId());
            dto.setCustomerName(order.getCustomer().getCustomerName());
            dto.setDate(order.getDate());
            dto.setTotal(order.getTotal());
            dto.setDiscount(order.getDiscount());
            dto.setSubTotal(order.getSubtotal());


            List<CartItemDto> cartItems = new ArrayList<>();
            order.getItems().forEach(orderDetail -> {
                CartItemDto cartItem = new CartItemDto();
                cartItem.setItemCode(orderDetail.getItemId());
                cartItem.setQty(orderDetail.getQty());
                ItemDTO item = (ItemDTO) itemService.getItemById(orderDetail.getItemId());
                if (item != null) {
                    cartItem.setDesc(item.getItemName());
                    cartItem.setUnitPrice(item.getPrice());
                    cartItem.setTotalPrice(cartItem.getUnitPrice() * cartItem.getQty());
                }
                cartItems.add(cartItem);
            });

            dto.setCartItems(cartItems);
            dtos.add(dto);
        });

        return dtos;
    }
}
