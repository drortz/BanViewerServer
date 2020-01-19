package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.Xml;

public interface XmlRepo extends JpaRepository<Xml, String>
{
	List<Xml> findByXmlIdIn(List<String> xmlIds);
}
