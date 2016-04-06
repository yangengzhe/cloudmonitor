/**
 * 
 */
package com.ices.csp.database.service;

import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.csp.database.dto.DatabaseConditionDto;
import com.ices.csp.database.dto.DatabaseDto;



/**
 * @author MFC
 *
 */
public interface DatabaseService {
	abstract public void create(DatabaseDto databaseDto) throws DtoException;
	abstract public void update(DatabaseDto databaseDto) throws DtoException;
	abstract public void delete(Integer id) throws BusinessException;
	abstract public List<DatabaseDto> query(DatabaseConditionDto dto, PagingBean pb) throws Exception;
	abstract public DatabaseDto query(Integer id) throws Exception;

}
