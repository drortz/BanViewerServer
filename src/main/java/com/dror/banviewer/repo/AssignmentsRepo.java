package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.Assignments;

public interface AssignmentsRepo extends JpaRepository<Assignments, String>  
{
	List<Assignments> findByOrderActionId(String orderActionId);
	List<Assignments> findByOrderActionIdIn(List<String> orderActionIds);
}
