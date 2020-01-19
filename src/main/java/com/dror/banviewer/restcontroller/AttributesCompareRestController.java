package com.dror.banviewer.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.service.AttributesCompareService;
import com.dror.banviewer.view.components.MainComponentsVersionsView;

@RestController
public class AttributesCompareRestController 
{
	@Autowired
	AttributesCompareService attributesCompareService;
	
	@CrossOrigin
	@PostMapping("/retrieveAttributesCompare")
	public List<MainComponentsVersionsView> retrieveAttributesCompare(@RequestBody RequestInput request)
	{
		return attributesCompareService.retrieveAttributesCompare(request);
	}
}
