package com.dror.banviewer.view;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserSkillsView 
{
	List<String> skills;
	List<String> securityProfiles;
	
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<String> getSecurityProfiles() {
		return securityProfiles;
	}
	public void setSecurityProfiles(List<String> securityProfiles) {
		this.securityProfiles = securityProfiles;
	}
	
	
}
