/**
 * 
 */
package com.ices.csp.database.service;

import java.util.ArrayList;
import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.csp.database.domain.Database;
import com.ices.csp.database.dto.DatabaseConditionDto;
import com.ices.csp.database.dto.DatabaseDto;
import com.ices.csp.software.domain.Software;



/**
 * @author MFC
 *
 */
@Service
public class DatabaseServiceImpl implements DatabaseService{
	/*
	 * 新增操作
	 * @param databaseDto
	 * 
	 */
	public void create(DatabaseDto databaseDto) throws DtoException{
		//将数据传输对象转换为实体对象
		Database database = (Database) DtoHelper.dismantle(Database.class, databaseDto);
		if(databaseDto.getSoftwareId()!=null){//软件类型不为空
			//根据id获取类型对象
			Software type=Software.query(databaseDto.getSoftwareId());
			database.setType(type);
		}
		Database.create(database);
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 * @param databaseDto
	 */
	public void update(DatabaseDto databaseDto) throws DtoException {
		Database database = Database.query(databaseDto.getId());
		Database dtoDatabase = (Database) DtoHelper.dismantle(Database.class, databaseDto);
		
		database.setName(databaseDto.getName());
		if(databaseDto.getSoftwareId()!=null){
			Software type=Software.query(databaseDto.getSoftwareId());
			database.setType(type);
		}
		
		database.setIp(dtoDatabase.getIp());
		database.setPort(dtoDatabase.getPort());
		database.setStorage(dtoDatabase.getStorage());
		database.setDemo(dtoDatabase.getDemo());
				
		Database.update(database);
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 * @param id
	 */
	public void delete(Integer id) throws BusinessException {
		Database database = null;
		database = Database.query(id);
		Database.delete(database);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据条件查询
	 * @param dto
	 * @param pb
	 * @return List<DatabaseDto>
	 * 
	 */
	public List<DatabaseDto> query(DatabaseConditionDto dto, PagingBean pb) throws Exception {
		//根据查询条件获取实体对象列表
		List<Database> list = Database.query(dto, pb);
		//定义数据传输对象列表
		List<DatabaseDto> dtoList = new ArrayList<DatabaseDto>();
		//检索实体对象列表中的每个实体对象
		for (Database database : list) {
			//将实体对象转换为数据传输对象
			DatabaseDto databaseDto=(DatabaseDto) DtoHelper.build(DatabaseDto.class, database);
			//获取实体对象属性type(类型为Software)
			Software type=database.getType();
			//设置数据传输对象的属性值
			if(type!=null){
				databaseDto.setSoftwareId(type.getId());
				databaseDto.setSoftwareName(type.getName());
			}
			//将数据传输对象加入到数据传输对象列表
			dtoList.add(databaseDto);
		}
		return dtoList;
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * @param id
	 * @return DatabaseDto
	 * 
	 */
	public DatabaseDto query(Integer id) throws Exception {
		Database database = Database.query(id);
		DatabaseDto dto = (DatabaseDto) DtoHelper.build(DatabaseDto.class, database);
		return dto;
	}

}
