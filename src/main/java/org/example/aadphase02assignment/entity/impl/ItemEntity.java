package org.example.aadphase02assignment.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemEntity {
    @Id
    private String itemCode;
    private String itemName;
    private int qto;
    private String author;
    private double price;
}
