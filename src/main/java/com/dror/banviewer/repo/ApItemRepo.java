package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.ApItem;


public interface ApItemRepo extends JpaRepository<ApItem, String> 
{
	List<ApItem> findByOrderActionId(String orderActionId);
	List<ApItem> findByOrderActionIdIn(List<String> orderActionId);
	List<ApItem> findByMainItemIdIn(List<String> mainItemId);
}
