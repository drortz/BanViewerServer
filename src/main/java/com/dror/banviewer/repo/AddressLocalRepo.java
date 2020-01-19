package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.AddressLocal;

public interface AddressLocalRepo extends JpaRepository<AddressLocal, String> 
{
	List<AddressLocal> findByApItemIdIn(List<String> apItemId);
}
