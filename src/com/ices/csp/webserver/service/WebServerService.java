/**
 * 
 */
package com.ices.csp.webserver.service;

import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.webserver.dto.WebServerConditionDto;
import com.ices.csp.webserver.dto.WebServerDto;


/**
 * @author MFC
 *
 */
public interface WebServerService {
	abstract public void create(WebServerDto webServerDto) throws DtoException;
	abstract public void update(WebServerDto webServerDto) throws DtoException;
	abstract public void delete(Integer id) throws BusinessException;
	abstract public List<WebServerDto> query(WebServerConditionDto dto, PagingBean pb) throws Exception;
	abstract public WebServerDto query(Integer id) throws Exception;
}
