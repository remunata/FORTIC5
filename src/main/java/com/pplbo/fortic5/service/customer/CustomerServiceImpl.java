package com.pplbo.fortic5.service.customer;

import com.pplbo.fortic5.model.user.Customer;
import com.pplbo.fortic5.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> optionalCustomer= customerRepository.findById(id);
        if(optionalCustomer.isPresent())
            return optionalCustomer.get();

        throw new NoSuchElementException("Customer ID not found");
    }
}
