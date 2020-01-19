package com.dror.banviewer.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.service.InterfacesService;
import com.dror.banviewer.view.InterfacesToOrderAction;

@RestController
public class InterfacesRestController 
{	
	@Autowired
	InterfacesService interfacesService;
	
	@CrossOrigin
	@PostMapping("/retrieveInterfaces")
	public List<InterfacesToOrderAction> retrieveInterfaces(@RequestBody RequestInput request)
	{
		return interfacesService.retrieveInterfaces(request);
	}
}
