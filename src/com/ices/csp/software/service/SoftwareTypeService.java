/**
 * 
 */
package com.ices.csp.software.service;

import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.software.domain.SoftwareType;
import com.ices.csp.software.dto.SoftwareTypeCondition;
import com.ices.csp.software.dto.SoftwareTypeDto;



/**
 * 服务接口
 * @author Fanchao Meng
 *
 */
public abstract interface SoftwareTypeService {
	abstract public void insert(SoftwareType softwareType) throws Exception;
	abstract public List<SoftwareType> getAll();
	
	abstract public List<SoftwareTypeDto> query(SoftwareTypeCondition dto, PagingBean pb) throws Exception;
	abstract public List<SoftwareTypeDto> query1(SoftwareTypeCondition dto, PagingBean pb) throws Exception;
	abstract public List<SoftwareTypeDto> getSoftwareTypes() throws Exception;
	abstract public SoftwareTypeDto query(Integer id) throws Exception;
	abstract public void add(SoftwareTypeDto softwareTypeDto) throws DtoException;
	abstract public void create(SoftwareTypeDto softwareTypeDto) throws DtoException;
	abstract public void update(SoftwareTypeDto softwareTypeDto) throws DtoException;
	abstract public void delete(Integer id) throws BusinessException;
}
