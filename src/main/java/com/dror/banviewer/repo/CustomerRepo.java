package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> 
{
	List<Customer> findByCustomerId(String customerId);
}
