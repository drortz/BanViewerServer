package com.dror.banviewer.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dror.banviewer.conf.DataSourceSetter;
import com.dror.banviewer.entity.ApItem;
import com.dror.banviewer.entity.Name;
import com.dror.banviewer.entity.OrderAction;
import com.dror.banviewer.modal.RequestInput;
import com.dror.banviewer.repo.ApItemRepo;
import com.dror.banviewer.repo.NameRepo;
import com.dror.banviewer.repo.OrderActionRepo;
import com.dror.banviewer.view.flow.OrderFlowView;
import com.dror.banviewer.view.flow.ProductFlowView;

@Service
public class OrderFlowViewService 
{
	@Autowired
	DataSourceSetter dataSourceSetter;
	
	@Autowired
	ApItemRepo apItemRepo;
	
	@Autowired
	NameRepo nameRepo;
	
	@Autowired
	OrderActionRepo orderActionRepo;
	
	private Map<String, String> orderActionTypeDecode = new HashMap<>();
	
	private Map<String, String> orderActuibStatusDecode = new HashMap<>();
	
	public List<OrderFlowView> retrieveOrderFlowView(RequestInput request)
	{
		dataSourceSetter.setDataSourceConnection(request.getDbInstance(), 
				request.getDbPassword(), 
				request.getDbUserName(),
				request.getIsDirectConnect(),
				request.getHost(),
				request.getPort(),
				request.getServiceName());
		
		initializeOrderActionTypeDecode();
		initializeOrderActionStatusDecode();
		
		return buildOrderFlowView(request.getBanId());
	}

	private void initializeOrderActionStatusDecode() 
	{
		// select * from tbdecode where decode_id like '%OrderActionStatus_%'; and language_id = 'EN';
		orderActuibStatusDecode.put("AM","Amended");
		orderActuibStatusDecode.put("BA","Being Amended");
		orderActuibStatusDecode.put("CA","Cancelled");
		orderActuibStatusDecode.put("DC","Discontinued");
		orderActuibStatusDecode.put("DE","Delivery");
		orderActuibStatusDecode.put("DO","Done");
		orderActuibStatusDecode.put("FI","Fictitious");
		orderActuibStatusDecode.put("FU","Future");
		orderActuibStatusDecode.put("IN","Initial");
		orderActuibStatusDecode.put("NE","Negotiation");
		orderActuibStatusDecode.put("NO","Completion");
		orderActuibStatusDecode.put("OH","On Hold");
		orderActuibStatusDecode.put("RE","Reject");
		orderActuibStatusDecode.put("TC","To Be Cancelled");
		orderActuibStatusDecode.put("DC","Discontinued");
		orderActuibStatusDecode.put("DE","Delivery");
		orderActuibStatusDecode.put("DO","Done");
		orderActuibStatusDecode.put("FI","Fictitious");
		orderActuibStatusDecode.put("FU","Future");
		orderActuibStatusDecode.put("IN","Initial");
		orderActuibStatusDecode.put("NE","Negotiation");
		orderActuibStatusDecode.put("NO","Completion");
		orderActuibStatusDecode.put("OH","On Hold");
		orderActuibStatusDecode.put("RE","Reject");
		orderActuibStatusDecode.put("TC","To Be Cancelled");
		orderActuibStatusDecode.put("AM","Amended");
		orderActuibStatusDecode.put("BA","Being Amended");
		orderActuibStatusDecode.put("CA","Cancelled");
	}

	private void initializeOrderActionTypeDecode() 
	{
		//select * from tbdecode where decode_id like '%AbsOrderActionName_%'
		//	and language_id = 'EN';
		
		orderActionTypeDecode.put("BA","Bulk Provide SMB Subscription");
		orderActionTypeDecode.put("MO","DynChargeEvent_MI");
		orderActionTypeDecode.put("MM","Cancel Change");
		orderActionTypeDecode.put("AW","AutoConvWireless");
		orderActionTypeDecode.put("CH","Change");
		orderActionTypeDecode.put("SSAM","Soft Suspend Amend");
		orderActionTypeDecode.put("RS","Resume");
		orderActionTypeDecode.put("PM","Change Ownership");
		orderActionTypeDecode.put("PE","Provide Existing");
		orderActionTypeDecode.put("PVAM","Provide Part of Move Amend");
		orderActionTypeDecode.put("CI","Move");
		orderActionTypeDecode.put("PR","Provide");
		orderActionTypeDecode.put("SR","Soft Resume");
		orderActionTypeDecode.put("PRAM","Provide Amend");
		orderActionTypeDecode.put("SRAM","Soft Resume Amend");
		orderActionTypeDecode.put("PS","Amend Provide Same As");
		orderActionTypeDecode.put("CE","Cease");
		orderActionTypeDecode.put("ND","Notify Discounts");
		orderActionTypeDecode.put("CB","Change Basic Pack");
		orderActionTypeDecode.put("CM","Cease Part of Move");
		orderActionTypeDecode.put("VS","Voluntary Suspend");
		orderActionTypeDecode.put("SS","Soft Suspend");
		orderActionTypeDecode.put("RG","Change Part of Replace Offer");
		orderActionTypeDecode.put("RM","Remove TV Packs");
		orderActionTypeDecode.put("CG","Return CPE");
		orderActionTypeDecode.put("CT","Change Business Offer Template");
		orderActionTypeDecode.put("MV","Move");
		orderActionTypeDecode.put("RO","Replace Offer");
		orderActionTypeDecode.put("SP","Swap Primary Line Indicator");
		orderActionTypeDecode.put("SUAM","Suspend Amend");
		orderActionTypeDecode.put("CX","Change-after-NP-Abort");
		orderActionTypeDecode.put("PV","Provide Part of Move");
		orderActionTypeDecode.put("FP","Provide Agreement");
		orderActionTypeDecode.put("SU","Suspend");
		orderActionTypeDecode.put("AS","Align Subscription");
		orderActionTypeDecode.put("RA","Remove in Change Part of Migrate");
		orderActionTypeDecode.put("CW","Change Ownership");
		orderActionTypeDecode.put("VR","Voluntary Resume");
		orderActionTypeDecode.put("FC","Change Agreement");
		orderActionTypeDecode.put("RC","Cease Part of Replace Offer");
		orderActionTypeDecode.put("CWAM","Change Ownership Amend");
		orderActionTypeDecode.put("PC","Cancel Change Part of Migrate");
		orderActionTypeDecode.put("CC","Conversion Change");
		orderActionTypeDecode.put("MI","Migrate");
		orderActionTypeDecode.put("CHAM","Change Amend");
		orderActionTypeDecode.put("ES","Re-establish");
		orderActionTypeDecode.put("AD","Add Product to Offer");
		orderActionTypeDecode.put("CS","Change SIM Card");
		orderActionTypeDecode.put("CEAM","Cease Amend");
		orderActionTypeDecode.put("PH","Change Part of Migrate");
		orderActionTypeDecode.put("CP","Change Package");
		orderActionTypeDecode.put("SA","Provide SMB Subscription");
	}

	private List<OrderFlowView> buildOrderFlowView(String banId) 
	{
		List<OrderFlowView> returnValue = new ArrayList<>();
		
		List<OrderAction> orderActions = orderActionRepo.findByCustomerId(banId);
		List<ApItem> apItems = apItemRepo.findByOrderActionIdIn(buildOrderActionsList(orderActions));
		
		
		List<String> orderIds = buildOrderIds(orderActions);
		
		Map<String, String> cidToName = new HashMap<>();
		
		for(String order : orderIds)
		{
			OrderFlowView orderFlowView = new OrderFlowView();
			List<ProductFlowView> productsFlowView = new ArrayList<>();
			
			orderFlowView.setOrderId(order);
			
			List<String> orderActionsOnOrder = retrieveOrderActionsOnOrder(order, orderActions);		
			List<ApItem> apItemsOnOrder = retrieveApMainItemsPerOrderActions(orderActionsOnOrder, apItems);
			
			for(ApItem ap : apItemsOnOrder)
			{
				ProductFlowView productView = new ProductFlowView();
				productView.setOrderActionId(ap.getOrderActionId());
				productView.setOrderActionType(retrieveOrderActionTypePerOrderActionId(ap.getOrderActionId(), orderActions));
				productView.setOrderActionStatus(retrieveOrderActionStatusPerOrderActionId(ap.getOrderActionId(), orderActions));
//				productView.setProductName(ap.getServiceType());
				
				if(cidToName.get(ap.getCid()) == null)
				{
					Name compName = nameRepo.findByCidAndPcVersion(ap.getCid(), ap.getPcVersion());
					cidToName.put(ap.getCid(), compName.getName() + " (" + ap.getServiceType() + ")");
				}

				productView.setProductName(cidToName.get(ap.getCid()));
				
				productsFlowView.add(productView);
			}
			
			productsFlowView.sort(Comparator.comparing(ProductFlowView::getOrderActionId));
			
			orderFlowView.setProductFlowView(productsFlowView);
			
			returnValue.add(orderFlowView);
		}
		
		returnValue.sort(Comparator.comparing(OrderFlowView::getOrderId));
		
		return returnValue;
	}

	private String retrieveOrderActionStatusPerOrderActionId(String orderActionId, List<OrderAction> orderActions) 
	{
		for(OrderAction oa : orderActions)
		{
			if(oa.getOrderActionId().equals(orderActionId))
			{
				String status = orderActuibStatusDecode.get(oa.getStatus());
				if(status != null)
				{
					return status;
				}
				
				return oa.getStatus();
			}
		}
		
		return null;
	}

	private String retrieveOrderActionTypePerOrderActionId(String orderActionId, List<OrderAction> orderActions) 
	{
		for(OrderAction oa : orderActions)
		{
			if(oa.getOrderActionId().equals(orderActionId))
			{
				String decodedAction = orderActionTypeDecode.get(oa.getActionType());
				if(decodedAction != null)
				{
					return decodedAction;					
				}
				
				return oa.getActionType();
			}
		}
		
		return null;
	}

	private List<String> retrieveOrderActionsOnOrder(String order, List<OrderAction> orderActions) 
	{
		List<String> returnValue = new ArrayList<>();
		
		for(OrderAction oa : orderActions)
		{
			if(oa.getOrderId().equals(order))
			{
				returnValue.add(oa.getOrderActionId());
			}
		}
		
		return returnValue;
	}

	private List<ApItem> retrieveApMainItemsPerOrderActions(List<String> orderActionsOnOrder, List<ApItem> apItems) 
	{
		List<ApItem> returnValue = new ArrayList<>();
		
		for(ApItem apItem : apItems)
		{
			if(apItem.getMainIndicator().equals("1") && orderActionsOnOrder.contains((apItem.getOrderActionId())) && !returnValue.contains(apItem.getOrderActionId()))
			{
				returnValue.add(apItem);
			}
		}
		
		return returnValue;
	}

	private List<String> buildOrderIds(List<OrderAction> orderActions) 
	{
		List<String> returnValue = new ArrayList<>();
		
		for(OrderAction oa : orderActions)
		{
			if(!returnValue.contains(oa.getOrderId()))
			{
				returnValue.add(oa.getOrderId());
			}
		}
		
		return returnValue;
	}

	private List<String> buildOrderActionsList(List<OrderAction> orderActions) 
	{
		List<String> returnValue = new ArrayList<>();
		
		for(OrderAction oa : orderActions)
		{
			returnValue.add(oa.getOrderActionId());
		}

		return returnValue;
	}
}
