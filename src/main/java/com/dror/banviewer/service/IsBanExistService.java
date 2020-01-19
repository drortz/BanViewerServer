package com.dror.banviewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dror.banviewer.entity.Customer;
import com.dror.banviewer.repo.CustomerRepo;

@Service
public class IsBanExistService 
{
	@Autowired
	CustomerRepo customerRepo;
	
	public boolean isBanExist(String banId)
	{
		List<Customer> customers = customerRepo.findByCustomerId(banId);
		return !customers.isEmpty();
	}
}
