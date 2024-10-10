package org.example.aadphase02assignment.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.SuperDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDto {
    String customerId;
    String customerName;
    String customerAddress;
    String customerPhone;
}
