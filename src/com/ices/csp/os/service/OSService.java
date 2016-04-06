package com.ices.csp.os.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.os.dto.OSDto;
import com.ices.csp.os.dto.OSVersionDto;

public interface OSService {
	
	public List<OSVersionDto> findOSVersionByosname(String osname) throws DtoException;

	public void deleteOSVersion(int[] deleteOSVersion);
	
	public void deleteOS(int[] deleteOS);

	public List<OSDto> findAllOS(PagingBean pb) throws DtoException;
	
	public List<OSVersionDto> findOSVersion(Integer id,PagingBean pb) throws DtoException;

	public List<OSDto> searchOS(Map<String, String> data) throws DtoException;

	public void addOS(OSDto osDto) throws Exception;

	public void updateOS(OSDto osDto) throws Exception;
	
	public void addOSVersion(OSVersionDto osversionDto) throws Exception;

	public void updateOSVersion(OSVersionDto osversionDto) throws Exception;
	
	public boolean findDuplicate(String osversionCode)throws DtoException;

}
