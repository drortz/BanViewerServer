package com.dror.banviewer.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.service.OrderActionService;
import com.dror.banviewer.view.OrderActionView;

@RestController
public class OrderActionRestController 
{
	@Autowired
	OrderActionService orderActionService;
	
	@CrossOrigin
	@PostMapping("/retrieveOrderActionDetails")
	public List<OrderActionView> retrieveOrderActionDetails(@RequestBody RequestInput request)
	{
		return orderActionService.retrieveOrderActionDetails(request);
	}
}
