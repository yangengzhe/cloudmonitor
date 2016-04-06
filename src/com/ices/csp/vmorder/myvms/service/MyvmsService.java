package com.ices.csp.vmorder.myvms.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.vmorder.myvms.dto.MonitorDto;
import com.ices.csp.vmorder.myvms.dto.MyvmsDto;

public interface MyvmsService {
	
	public List<MonitorDto> myvmsMonitor(Integer vmitemMark) throws DtoException;
	
	public List<MyvmsDto> findAllMyvms(PagingBean pb) throws DtoException, ParseException;
	
	public List<MyvmsDto> findAllFinalMyvms(PagingBean pb) throws DtoException, ParseException;
	
	public List<MyvmsDto> searchMyvms(Map<String, String> data) throws DtoException;
	
	public List<MyvmsDto> searchFinalMyvms(Map<String, String> data) throws DtoException;
	
	public void updateVMItem(MyvmsDto myvmsDto) throws Exception;
	
	public int startVMItem(Integer myvmsid) throws Exception;
	
	public int shutdownVMItem(Integer myvmsid) throws Exception;
}
