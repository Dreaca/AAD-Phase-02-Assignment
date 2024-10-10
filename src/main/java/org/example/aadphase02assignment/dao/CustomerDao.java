package org.example.aadphase02assignment.dao;

import org.example.aadphase02assignment.entity.impl.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<CustomerEntity,String> {
}
