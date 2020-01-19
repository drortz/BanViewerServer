package com.dror.banviewer.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.AllTables;


public interface AllTablesRepository extends JpaRepository<AllTables, String> 
{
	List <AllTables> findByTableNameIn(List <String> tableNames);
}
