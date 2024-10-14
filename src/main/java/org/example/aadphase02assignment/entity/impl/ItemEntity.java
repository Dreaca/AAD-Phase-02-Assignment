package org.example.aadphase02assignment.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.entity.SuperEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemEntity implements SuperEntity {
    @Id
    private String itemCode;
    private String itemName;
    private int qto;
    private String author;
    private double price;
    @ManyToMany(mappedBy = "items")
    private List<OrderEntity> orders;
}
