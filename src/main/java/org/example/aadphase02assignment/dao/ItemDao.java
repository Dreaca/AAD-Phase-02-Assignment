package org.example.aadphase02assignment.dao;

import org.example.aadphase02assignment.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemDao extends JpaRepository<ItemEntity, String> {
}
