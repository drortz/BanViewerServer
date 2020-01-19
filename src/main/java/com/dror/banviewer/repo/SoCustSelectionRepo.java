package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.SoCustSelection;

public interface SoCustSelectionRepo extends JpaRepository<SoCustSelection, String>
{
	List<SoCustSelection> findByOrderActionIdAndStatus(String oa, String status);
	List<SoCustSelection> findByOrderActionIdInAndStatus(List<String> oa, String status);
}
