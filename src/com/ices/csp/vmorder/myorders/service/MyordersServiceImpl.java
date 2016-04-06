package com.ices.csp.vmorder.myorders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.springframework.stereotype.Service;

import com.ices.csp.common.enumitem.VMOrderState;
import com.ices.csp.vmorder.domain.VMItem;
import com.ices.csp.vmorder.domain.VMOrder;
import com.ices.csp.vmorder.myorders.dto.MyitemsDto;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;

@Service
public class MyordersServiceImpl implements MyordersService{
	@Override
	public List<MyordersDto> findAllMyorders(PagingBean pb) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		List<VMOrder> result = VMOrder.findAll(pb);
		for (VMOrder vmorder : result) {
			MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
			resultList.add(myordersDto);
		}
		return resultList;
	}
	@Override
	public List<MyordersDto> findAllFinalMyorders(PagingBean pb) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		List<VMOrder> result = VMOrder.findAllFinalorders(pb);
		for (VMOrder vmorder : result) {
			MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
			resultList.add(myordersDto);
		}
		return resultList;
	}
	@Override
	public List<MyitemsDto> findItemsByOrderId(Integer orderid) throws DtoException {
		List<MyitemsDto> resultList = new ArrayList<MyitemsDto>();
		List<VMItem> result = VMItem.findItemsByOrderId(orderid);
		for (VMItem vmitem : result) {
			MyitemsDto myitemsDto = DtoHelper.build(MyitemsDto.class, vmitem);
			myitemsDto.setNodeName(vmitem.getNode().getName());
			myitemsDto.setState(vmitem.getState().getValue());
			String renttime = vmitem.getTenancy().toString()+vmitem.getUnit().getValue();
			myitemsDto.setRenttime(renttime);
			if(vmitem.getServer()!=null){
				myitemsDto.setServerName(vmitem.getServer().getName());
			}
			
			resultList.add(myitemsDto);
		}
		return resultList;
	}
	@Override
	public List<MyordersDto> searchMyorders(Map<String, String> data) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		VMOrderState state = null;
		if(data.get("state")!=null){
			state = VMOrderState.getMatchByName(data.get("state"));
		}
		try{
			List<VMOrder> result = VMOrder.findByParam(data.get("number"),state,data.get("formdate"),data.get("todate"));
			for (VMOrder vmorder : result) {
				MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
				resultList.add(myordersDto);
			}
			return resultList;
			
			}catch(Exception e){
				return resultList;
			
			}

	}
	@Override
	public List<MyordersDto> searchFinalorders(Map<String, String> data) throws DtoException {
		List<MyordersDto> resultList = new ArrayList<MyordersDto>();
		Enterprise enterprise = null;
		if(data.get("enterprise")!=null){
			enterprise = Enterprise.findByName(data.get("enterprise"));
		}
		try{
			List<VMOrder> result = VMOrder.findByParam2(data.get("number"),enterprise,data.get("formdate"),data.get("todate"));
			for (VMOrder vmorder : result) {
				MyordersDto myordersDto = DtoHelper.build(MyordersDto.class, vmorder);
				resultList.add(myordersDto);
			}
			return resultList;
			
			}catch(Exception e){
				return resultList;
			
			}

	}
}
