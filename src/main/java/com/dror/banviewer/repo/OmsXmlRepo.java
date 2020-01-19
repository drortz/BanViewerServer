package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.Assignments;
import com.dror.banviewer.entity.omsXml;

public interface OmsXmlRepo extends JpaRepository<omsXml, Integer>
{
	List<omsXml> findBystepInstanceId(String stepInsId);
	List<omsXml> findBystepInstanceIdContaining(String stepInsId);
	List<omsXml> findByStepInstanceIdIn(List<String> stepInstanceId);
}
