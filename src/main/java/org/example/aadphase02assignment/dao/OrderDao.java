package org.example.aadphase02assignment.dao;

import org.example.aadphase02assignment.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity,String> {
}
