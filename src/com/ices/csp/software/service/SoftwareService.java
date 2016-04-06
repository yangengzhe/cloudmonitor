/**
 * 
 */
package com.ices.csp.software.service;

import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.software.domain.Software;
import com.ices.csp.software.dto.SoftwareConditionDto;
import com.ices.csp.software.dto.SoftwareDto;



/**
 * @author Fanchao Meng
 *
 */
public abstract interface SoftwareService {
	abstract public void create(Software software) throws Exception;
	
	abstract public void update(SoftwareDto softwareDto) throws DtoException;
	abstract public void delete(Integer id) throws BusinessException;
	abstract public List<Software> query();
	abstract public void create(SoftwareDto softwareDto) throws DtoException;
	abstract public List<SoftwareDto> query(SoftwareConditionDto dto, PagingBean pb) throws Exception;
	abstract public SoftwareDto query(Integer id) throws Exception;
	abstract public List<SoftwareDto> getSoftwares(String code) throws Exception;
}
