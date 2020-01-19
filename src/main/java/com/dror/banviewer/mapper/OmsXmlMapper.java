package com.dror.banviewer.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dror.banviewer.entity.omsXml;
import com.dror.banviewer.view.omsXmlView;

@Component
public class OmsXmlMapper 
{
	public void mapAssignments(List<omsXml> src, List<omsXmlView> trg)
	{
		for(omsXml omsXml: src)
		{
			omsXmlView omsXmlView = new omsXmlView();
			
			omsXmlView.setFileContent(omsXml.getFileContent());
			omsXmlView.setInterfaceName(omsXml.getInterfaceName());
			omsXmlView.setStepInstanceId(omsXml.getStepInstanceId());
			omsXmlView.setTime(omsXml.getTime());
			omsXmlView.setXmlType(omsXml.getXmlType());
			
			trg.add(omsXmlView);
		}
	}
}
