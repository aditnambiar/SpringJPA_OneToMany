package com.cred.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cred.io.model.Customer;
import com.cred.io.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCustomerId(Long customerId); 

}
