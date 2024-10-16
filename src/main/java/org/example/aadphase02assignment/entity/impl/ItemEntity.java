package org.example.aadphase02assignment.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.entity.SuperEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemCode;
    private String itemName;
    private int qto; // This can be used for inventory or stock count
    private String author;
    private double price;

    // Change from List<OrderEntity> to List<OrderItemEntity>
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems; // Now holds OrderItemEntity
}