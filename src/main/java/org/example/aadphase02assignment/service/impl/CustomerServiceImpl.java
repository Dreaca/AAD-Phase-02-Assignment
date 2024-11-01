/*
 * Copyright 2024 Buddhika Pathum
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.example.aadphase02assignment.service.impl;

import org.example.aadphase02assignment.customStatusCodes.SelectedItemCustomerOrderErrorStatusCodes;
import org.example.aadphase02assignment.dao.CustomerDao;
import org.example.aadphase02assignment.dto.CustomerStatus;
import org.example.aadphase02assignment.dto.impl.CustomerDTO;
import org.example.aadphase02assignment.entity.impl.CustomerEntity;
import org.example.aadphase02assignment.entity.impl.ItemEntity;
import org.example.aadphase02assignment.exceptions.CustomerNotFoundException;
import org.example.aadphase02assignment.exceptions.DataPersistException;
import org.example.aadphase02assignment.service.CustomerService;
import org.example.aadphase02assignment.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity saved = customerDao.save(mapping.toCustomerEntity(customerDTO));
        if (saved == null) {
            throw new DataPersistException("Customer not saved");
        }
    }

    @Override
    public CustomerStatus getCustomer(String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            throw new DataPersistException("Customer id is empty");
        } else if (customerDao.existsById(customerId)) {
            CustomerEntity customer = customerDao.getReferenceById(customerId);
            return  mapping.toCustomerDTO(customer);
        }
        return new SelectedItemCustomerOrderErrorStatusCodes(2,"Customer with ID"+customerId+" not found");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.toCustomerDTOList(customerDao.findAll());
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> existedUser = customerDao.findById(customerId);
        if(!existedUser.isPresent()){
            throw new CustomerNotFoundException("Customer with id "+customerId+" not found");
        }else {
            customerDao.deleteById(customerId);
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        Optional<CustomerEntity> existedUser = customerDao.findById(customerId);
        if(existedUser.isPresent()){
           existedUser.get().setCustomerName(customerDTO.getCustomerName());
           existedUser.get().setCustomerAddress(customerDTO.getCustomerAddress());
           existedUser.get().setCustomerPhone(customerDTO.getCustomerPhone());
        }
    }
    @Override
    public List<String> findSuggestions(String query) {
        List<CustomerEntity> list = customerDao.findAll();

        return list.stream()
                .filter(customerEntity -> customerEntity.getCustomerName().toLowerCase().contains(query.toLowerCase()))
                .map(customerEntity -> String.format(" Name: %s - Address: %s - Phone: %s", customerEntity.getCustomerName(), customerEntity.getCustomerAddress(), customerEntity.getCustomerPhone()))
                .collect(Collectors.toList());
    }
}
