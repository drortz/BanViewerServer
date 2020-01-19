package com.dror.banviewer.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.service.OrderFlowViewService;
import com.dror.banviewer.view.flow.OrderFlowView;

@RestController
public class FlowDetailsRestController 
{
	@Autowired
	private OrderFlowViewService orderFlowViewService;
	
	@CrossOrigin
	@PostMapping("/retrieveOrderFlowView")
	public List<OrderFlowView> retrieveOrderFlowView(@RequestBody RequestInput request)
	{
		return orderFlowViewService.retrieveOrderFlowView(request);
	}
}
