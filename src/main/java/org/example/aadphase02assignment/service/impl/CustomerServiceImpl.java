package org.example.aadphase02assignment.service.impl;

import org.example.aadphase02assignment.customStatusCodes.SelectedItemCustomerOrderErrorStatusCodes;
import org.example.aadphase02assignment.dao.CustomerDao;
import org.example.aadphase02assignment.dto.CustomerStatus;
import org.example.aadphase02assignment.dto.impl.CustomerDTO;
import org.example.aadphase02assignment.entity.impl.CustomerEntity;
import org.example.aadphase02assignment.exceptions.CustomerNotFoundException;
import org.example.aadphase02assignment.exceptions.DataPersistException;
import org.example.aadphase02assignment.service.CustomerService;
import org.example.aadphase02assignment.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
