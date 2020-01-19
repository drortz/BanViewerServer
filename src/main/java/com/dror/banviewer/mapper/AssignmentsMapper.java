package com.dror.banviewer.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dror.banviewer.entity.Assignments;
import com.dror.banviewer.view.AssignmentsView;

@Component
public class AssignmentsMapper 
{
	public void mapAssignments(List<Assignments> src, List<AssignmentsView> trg)
	{
		for(Assignments assignmentSrc: src)
		{
			AssignmentsView assViewToAdd = new AssignmentsView();
			
			assViewToAdd.setStepInstanceId(assignmentSrc.getStepInstanceId());
			assViewToAdd.setOrderActionId(assignmentSrc.getOrderActionId());
//			assViewToAdd.setDisaplyName(assignmentSrc.getDisaplyName());
			assViewToAdd.setState(assignmentSrc.getState());
			
			trg.add(assViewToAdd);
		}
	}
}
