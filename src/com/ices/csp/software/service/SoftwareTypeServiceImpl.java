/**
 * 
 */
package com.ices.csp.software.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;

import com.ices.csp.software.domain.SoftwareType;
import com.ices.csp.software.dto.SoftwareTypeCondition;
import com.ices.csp.software.dto.SoftwareTypeDto;


/**
 * 服务接口实现
 * @author Fanchao Meng
 *
 */
@Service
public class SoftwareTypeServiceImpl implements SoftwareTypeService {
	
	public void insert(SoftwareType softwareType) throws Exception{
		SoftwareType.insert(softwareType);
	}
	public List<SoftwareType> getAll(){
		System.out.println("开始查询...");
		return SoftwareType.getAll();
	}
	
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * @param dto
	 * @param pb
	 * @return List<SoftwareTypeDto>
	 */
	public List<SoftwareTypeDto> query(SoftwareTypeCondition dto, PagingBean pb) throws Exception {
		List<SoftwareType> list = SoftwareType.query(dto, pb);
		List<SoftwareTypeDto> dtoList = new ArrayList<SoftwareTypeDto>();
		for (SoftwareType softwareType : list) {
			dtoList.add((SoftwareTypeDto) DtoHelper.build(SoftwareTypeDto.class, softwareType));
		}
		return dtoList;
	}
	
	public List<SoftwareTypeDto> query1(SoftwareTypeCondition dto, PagingBean pb) throws Exception {
		//根据查询条件获取实体对象列表
		List<SoftwareType> list = SoftwareType.query(dto, pb);
		//定义数据传输对象列表
		List<SoftwareTypeDto> dtoList = new ArrayList<SoftwareTypeDto>();
		//检索实体对象列表中的每个实体对象
		for (SoftwareType softwareType : list) {
			//将实体对象转换为数据传输对象
			SoftwareTypeDto softwareTypeDto=(SoftwareTypeDto) DtoHelper.build(SoftwareTypeDto.class, softwareType);
			//获取实体对象属性parent(类型为SoftwareType)
			SoftwareType parent=softwareType.getParent();
			//设置数据传输对象的属性值
			if(parent!=null){
				//System.out.println("父类型1："+parent.getId()+","+parent.getName());
				softwareTypeDto.setParentId(parent.getId());
				softwareTypeDto.setParentName(parent.getName());
				//System.out.println("父类型2："+softwareTypeDto.getParentId()+","+softwareTypeDto.getParentName());
			}
			//将数据传输对象加入到数据传输对象列表
			dtoList.add(softwareTypeDto);
		}
		return dtoList;
	}
	
	public List<SoftwareTypeDto> getSoftwareTypes() throws Exception{
		//获取所有实体对象
		List<SoftwareType> list = SoftwareType.getAll();
		//定义数据传输对象列表
		List<SoftwareTypeDto> dtoList = new ArrayList<SoftwareTypeDto>();
		for (SoftwareType softwareType : list) {
			//将实体对象转换为数据传输对象
			SoftwareTypeDto softwareTypeDto=(SoftwareTypeDto) DtoHelper.build(SoftwareTypeDto.class, softwareType);
			//将数据传输对象加入到数据传输对象列表
			dtoList.add(softwareTypeDto);
		}
		return dtoList;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * @param id
	 * @return SoftwareTypeDto
	 * 
	 */
	public SoftwareTypeDto query(Integer id) throws Exception {
		SoftwareType softwareType = SoftwareType.query(id);
		SoftwareTypeDto dto = (SoftwareTypeDto) DtoHelper.build(SoftwareTypeDto.class, softwareType);
		return dto;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 新增操作1
	 * @param softwareTypeDto
	 */
	public void add(SoftwareTypeDto softwareTypeDto) throws DtoException {
		SoftwareType softwareType = (SoftwareType) DtoHelper.dismantle(SoftwareType.class, softwareTypeDto);
		SoftwareType.add(softwareType);
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 新增操作2
	 * 
	 */
	public void create(SoftwareTypeDto softwareTypeDto) throws DtoException{
		//将数据传输对象转换为实体对象
		SoftwareType softwareType = (SoftwareType) DtoHelper.dismantle(SoftwareType.class, softwareTypeDto);
		
		if(softwareTypeDto.getParentId()!=null){//父类型不为空
			//根据id获取父类型对象
			SoftwareType parent=SoftwareType.query(softwareTypeDto.getParentId());
			softwareType.setParent(parent);
		}
		SoftwareType.add(softwareType);
	}
	//////////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 * @param softwareTypeDto
	 */
	public void update(SoftwareTypeDto softwareTypeDto) throws DtoException {
		SoftwareType softwareType = SoftwareType.query(softwareTypeDto.getId());
		SoftwareType dtoSoftwareType = (SoftwareType) DtoHelper.dismantle(SoftwareType.class, softwareTypeDto);
		softwareType.setCode(dtoSoftwareType.getCode());
		softwareType.setName(dtoSoftwareType.getName());
		softwareType.setDemo(dtoSoftwareType.getDemo());
		if(softwareTypeDto.getParentId()!=null){
			SoftwareType parent=SoftwareType.query(softwareTypeDto.getParentId());
			softwareType.setParent(parent);
		}
		SoftwareType.update(softwareType);
	}
		
	///////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 * @param id
	 */
	public void delete(Integer id) throws BusinessException {
		SoftwareType softwareType = null;
		softwareType = SoftwareType.query(id);
		SoftwareType.delete(softwareType);
	}
	
	

}
