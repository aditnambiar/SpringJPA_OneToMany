package com.cred.io.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cred.io.model.Product;
import com.cred.io.repository.CustomerRepository;
import com.cred.io.repository.ProductRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired 
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers/{id}/products")
	public List<Product> getProductByCustomerId (@PathVariable Long id) throws NotFoundException {
		
		 if(!customerRepository.existsById(id)) {
	            throw new NotFoundException("Customer not found!");
	     }
	      
	     return productRepository.findByCustomerId(id);
	
	}
	
	@PostMapping("customers/{id}/products") 
	public Product addProduct(@PathVariable Long id, @RequestBody Product product) throws NotFoundException {
		return customerRepository.findById(id)
                .map(customer -> {
                    product.setCustomer(customer);
                    return productRepository.save(product);
                }).orElseThrow(() -> new NotFoundException("Customer not found!"));
		
	}
	
	
	@PutMapping("/customers/{id}/products/{productId}")
    public Product updateProduct(@PathVariable Long id,
                    @PathVariable Long productId,
                    @RequestBody Product productUpdated) throws NotFoundException {
      
      if(!customerRepository.existsById(id)) {
        throw new NotFoundException("Customer not found!");
      }
      
        return productRepository.findById(productId)
                .map(product -> {
                    product.setName(productUpdated.getName());
                    product.setQuantity(productUpdated.getQuantity());
                    return productRepository.save(product);
                }).orElseThrow(() -> new NotFoundException("Product not found!"));
    }
	
	@DeleteMapping("/customers/{id}/products/{productId}")
    public String deleteAssignment(@PathVariable Long id,
                     @PathVariable Long productId) throws NotFoundException {
      
      if(!customerRepository.existsById(id)) {
        throw new NotFoundException("Customer not found!");
      }
      
        return productRepository.findById(productId)
                .map(assignment -> {
                    productRepository.delete(assignment);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Product not found!"));
    }
	
	

}
