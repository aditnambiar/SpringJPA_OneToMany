package com.cred.io.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(appliesTo = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	 private String name;
	  
	@Column(name = "age")
	private int age;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Product> products;
	
	public Customer() {};
	
	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	

}
