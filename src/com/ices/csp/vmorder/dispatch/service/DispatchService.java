package com.ices.csp.vmorder.dispatch.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.vmorder.myorders.dto.MyordersDto;

public interface DispatchService {
	public List<MyordersDto> findVMOrderForDispatch(PagingBean pb) throws DtoException;
	public List<MyordersDto> searchDispatch(Map<String, String> data) throws DtoException;
	public void changestateDispatch(MyordersDto myordersDto) throws Exception;
	public void changestateCancel(int[] canceldispatch) throws Exception;
	public void deleteDispatch(int[] canceldispatch) throws Exception;
}
