package com.ices.csp.vmorder.createvms.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.vmorder.myorders.dto.MyordersDto;
import com.ices.csp.vmorder.myvms.dto.MyvmsDto;

public interface CreatevmsService {
	public List<MyordersDto> findVMOrderForCreatevms(PagingBean pb) throws DtoException;
	public List<MyordersDto> searchCreatevms(Map<String, String> data) throws DtoException;
	
	public void commitCreatevmsedit(MyvmsDto myvmsDto) throws Exception;
	
	public void changestateCreatevms(Integer orderid) throws Exception;
}
