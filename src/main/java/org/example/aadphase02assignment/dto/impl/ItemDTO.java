package org.example.aadphase02assignment.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.dto.ItemStatus;
import org.example.aadphase02assignment.dto.SuperDto;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements ItemStatus {
    private String itemCode;
    private String itemName;
    private int qto;
    private String author;
    private int price;
}
