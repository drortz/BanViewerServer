package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.OrderActionImpl;

public interface OrderActionImplRepo extends JpaRepository<OrderActionImpl, String> 
{
	OrderActionImpl findByOrderActionId(String orderUnitId);
	List<OrderActionImpl> findByOrderActionIdIn(List<String> orderUnitId);
}
