/*
 * Copyright 2024 Buddhika Pathum
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.example.aadphase02assignment.util;

import org.example.aadphase02assignment.dto.impl.CustomerDTO;
import org.example.aadphase02assignment.dto.impl.ItemDTO;
import org.example.aadphase02assignment.dto.impl.OrderDTO;
import org.example.aadphase02assignment.dto.impl.OrderDetailDto;
import org.example.aadphase02assignment.entity.impl.CustomerEntity;
import org.example.aadphase02assignment.entity.impl.ItemEntity;
import org.example.aadphase02assignment.entity.impl.OrderEntity;
import org.example.aadphase02assignment.entity.impl.OrderItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> toCustomerDTOList(List<CustomerEntity> customerEntityList) {
        return modelMapper.map(customerEntityList, new TypeToken<List<CustomerDTO>>() {}.getType());
    }
    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public ItemDTO toItemDTO(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDTO.class);
    }
    public List<ItemDTO> toItemDTOList(List<ItemEntity> itemEntityList) {
        return modelMapper.map(itemEntityList, new TypeToken<List<ItemDTO>>() {}.getType());
    }
    public OrderEntity toOrderEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }
    public OrderDTO toOrderDTO(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDTO.class);
    }
    public List<OrderDTO> toOrderDTOList(List<OrderEntity> orderEntities) {
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntities) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(orderEntity.getOrderId());
            orderDTO.setCustomer(toCustomerDTO(orderEntity.getCustomer()));
            orderDTO.setDate(orderEntity.getOrderDate());
            orderDTO.setTotal(orderEntity.getTotalPrice());
            orderDTO.setDiscount(orderEntity.getDiscount());
            orderDTO.setTotal(orderEntity.getSubTotal());

            List<OrderDetailDto> orderDetails = new ArrayList<>();

            for (OrderItemEntity orderItemEntity : orderEntity.getOrderItems()) {
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                orderDetailDto.setId(orderItemEntity.getId());
                orderDetailDto.setOrderId(orderItemEntity.getOrder().getOrderId());
                orderDetailDto.setItemId(orderItemEntity.getItem().getItemCode());
                orderDetailDto.setQty(orderItemEntity.getQuantity());
                orderDetails.add(orderDetailDto);
            }

            orderDTO.setItems(orderDetails);

            orderDTOs.add(orderDTO);
        }

        return orderDTOs;
    }

}
