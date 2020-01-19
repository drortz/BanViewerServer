package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApPricePlanRepo extends JpaRepository<com.dror.banviewer.entity.ApPricePlan, String>  
{
	List<com.dror.banviewer.entity.ApPricePlan> findByOrderActionId(String orderAction);
	List<com.dror.banviewer.entity.ApPricePlan> findByOrderActionIdIn(List<String> orderAction);
}
