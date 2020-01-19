package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.Name;

public interface NameRepo extends JpaRepository<Name, String>
{
	Name findByCidAndPcVersion(String cid, String pcVersion);
//	List<Name> findByCidInWithMaxPcVersion(List<String> cid);
}
