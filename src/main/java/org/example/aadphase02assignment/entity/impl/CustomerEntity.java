package org.example.aadphase02assignment.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aadphase02assignment.entity.SuperEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity {
@Id
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    @OneToMany(mappedBy = "orderId")
    private List<OrderEntity> order;
}
