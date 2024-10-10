package org.example.aadphase02assignment.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.SuperDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDto implements SuperDto {
    private String itemCode;
    private String desc;
    private double unitPrice;
    private int qty;
    private double totalPrice;
}
