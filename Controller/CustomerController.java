package com.cred.io.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cred.io.model.Customer;
import com.cred.io.repository.CustomerRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllStudents() {
	    return customerRepository.findAll();
	}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomerByID(@PathVariable Long id) throws NotFoundException {
		
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if(optCustomer.isPresent()) {
			return optCustomer.get();
		} else {
			throw new NotFoundException("Customer not found with ID " + id);
		}
	}
	
	@PostMapping("/customers") 
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) throws NotFoundException {
		return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    customer.setAge(updatedCustomer.getAge());
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new NotFoundException("Customer not found with ID " + id));
	}
	
	@DeleteMapping("customers/{id}") 
	public String deleteCustomer(@PathVariable Long id) throws NotFoundException {
		return customerRepository.findById(id)
                .map(student -> {
                    customerRepository.delete(student);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Student not found with id " + id));
		
	}

}
