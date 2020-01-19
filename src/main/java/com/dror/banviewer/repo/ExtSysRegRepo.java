package com.dror.banviewer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dror.banviewer.entity.ExtSysReg;

public interface ExtSysRegRepo extends JpaRepository<ExtSysReg, String>
{
	List<ExtSysReg> findByOrderActionIdInAndExtSystem(List<String> oa, String ext);
}
