package org.example.aadphase02assignment.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.entity.SuperEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class OrderEntity implements SuperEntity {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerEntity customer;
    private String orderDate;
    private Double totalPrice;
    private String discount;
    private Double subTotal;
    @ManyToMany(mappedBy = "itemId")
    private List<ItemEntity> items;

}
