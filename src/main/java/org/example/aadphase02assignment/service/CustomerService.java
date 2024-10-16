package org.example.aadphase02assignment.service;

import org.example.aadphase02assignment.dto.CustomerStatus;
import org.example.aadphase02assignment.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    CustomerStatus getCustomer(String customerId);
    List<CustomerDTO> getAllCustomers();
    void deleteCustomer(String customerId);
    void updateCustomer(String customerId, CustomerDTO customerDTO);

    List<String> findSuggestions(String trim);
}
