package org.example.aadphase02assignment.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.SuperDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto implements SuperDto {
    private long id;
    private String orderId;
    private String itemId;
    private int qty;

}
