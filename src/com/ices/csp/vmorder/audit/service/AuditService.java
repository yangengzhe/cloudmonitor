package com.ices.csp.vmorder.audit.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.vmorder.myorders.dto.MyordersDto;

public interface AuditService {
	public List<MyordersDto> findVMOrderISApply(PagingBean pb) throws DtoException;
	public List<MyordersDto> searchAudit(Map<String, String> data) throws DtoException;
	public void commitAudit(MyordersDto myordersDto) throws Exception;
	public void createvmitem(int[] vmsid);
	public void allvmitemnocreate(Integer vmid);
	public void changestateAudit(Integer orderid) throws Exception;
}
