package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dror.banviewer.entity.OrderAction;


public interface OrderActionRepo extends JpaRepository<OrderAction, String> 
{
	List<OrderAction> findByCustomerId(String customerId);
	OrderAction findByOrderActionId(String orderUnitId);
}
