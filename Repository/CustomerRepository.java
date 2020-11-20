package com.cred.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cred.io.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
