package org.example.aadphase02assignment.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.SuperDto;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements SuperDto{
    private String orderId;
    private String customerId;
    private String customerName;
    private String date;
    private double total;
    private String discount;
    private double subtotal;
}
