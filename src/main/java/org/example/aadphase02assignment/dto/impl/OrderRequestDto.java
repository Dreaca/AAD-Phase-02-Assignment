package org.example.aadphase02assignment.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.SuperDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto implements SuperDto {
    private String orderId;
    private String customerId;
    private String date;
    private String customerName;
    private double total;
    private String discount;
    private double subTotal;

    private List<CartItemDto> cartItems;
}
