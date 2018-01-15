package com.bootcrm.service.imp;

import com.bootcrm.mapper.CustomerMapper;
import com.bootcrm.model.Customer;
import com.bootcrm.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public void insert(Customer customer) {
            customerMapper.insert(customer);
    }

}
