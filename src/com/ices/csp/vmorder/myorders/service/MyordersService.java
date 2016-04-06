package com.ices.csp.vmorder.myorders.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.vmorder.myorders.dto.MyordersDto;
import com.ices.csp.vmorder.myorders.dto.MyitemsDto;

public interface MyordersService {
	public List<MyordersDto> findAllMyorders(PagingBean pb) throws DtoException;
	
	public List<MyitemsDto> findItemsByOrderId(Integer orderid) throws DtoException;
	
	public List<MyordersDto> searchMyorders(Map<String, String> data) throws DtoException;
	
	public List<MyordersDto> findAllFinalMyorders(PagingBean pb) throws DtoException;
	
	public List<MyordersDto> searchFinalorders(Map<String, String> data) throws DtoException;
}
