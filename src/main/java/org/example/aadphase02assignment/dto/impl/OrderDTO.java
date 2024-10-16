package org.example.aadphase02assignment.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.SuperDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements SuperDto{
    private String orderId;
    private CustomerDTO customer;
    private String date;
    private double total;
    private String discount;
    private double subtotal;
    private List<OrderDetailDto>items;
}
