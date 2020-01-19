package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.BillingCharge;

public interface BillingChargeRepo extends JpaRepository<BillingCharge, String>  
{
	BillingCharge findByApIdAndVersion(String apId, String version);
	List<BillingCharge> findByApIdIn(List<String> apId);
}
