/**
 * 
 */
package com.ices.csp.software.service;

import java.util.ArrayList;
import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.csp.software.domain.Software;
import com.ices.csp.software.domain.SoftwareType;
import com.ices.csp.software.dto.SoftwareConditionDto;
import com.ices.csp.software.dto.SoftwareDto;


/**
 * @author Fanchao Meng
 *
 */
@Service
public class SoftwareServiceImpl implements SoftwareService{
	public void create(Software softwaree) throws Exception{
		Software.create(softwaree);
	}
	public List<Software> query(){
		return Software.query();
	}
	////////////////////////////////////////////////////////////////////////////
	/*
	 * 新增操作
	 * @param softwareDto
	 * 
	 */
	public void create(SoftwareDto softwareDto) throws DtoException{
		//将数据传输对象转换为实体对象
		Software software = (Software) DtoHelper.dismantle(Software.class, softwareDto);
		if(softwareDto.getTypeId()!=null){//软件类型不为空
			//根据id获取父类型对象
			SoftwareType type=SoftwareType.query(softwareDto.getTypeId());
			software.setType(type);
		}
		Software.create(software);
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 * @param softwareDto
	 */
	public void update(SoftwareDto softwareDto) throws DtoException {
		Software software = Software.query(softwareDto.getId());
		Software dtoSoftware = (Software) DtoHelper.dismantle(Software.class, softwareDto);
		software.setCode(dtoSoftware.getCode());
		software.setName(dtoSoftware.getName());
		software.setProvider(dtoSoftware.getProvider());
		software.setSize(dtoSoftware.getSize());
		software.setDemo(dtoSoftware.getDemo());
		if(softwareDto.getTypeId()!=null){
			SoftwareType type=SoftwareType.query(softwareDto.getTypeId());
			software.setType(type);
		}
		Software.update(software);
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 * @param id
	 */
	public void delete(Integer id) throws BusinessException {
		Software software = null;
		software = Software.query(id);
		Software.delete(software);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据条件查询
	 * @param dto
	 * @param pb
	 * @return List<SoftwareDto>
	 * 
	 */
	public List<SoftwareDto> query(SoftwareConditionDto dto, PagingBean pb) throws Exception {
		//根据查询条件获取实体对象列表
		List<Software> list = Software.query(dto, pb);
		//定义数据传输对象列表
		List<SoftwareDto> dtoList = new ArrayList<SoftwareDto>();
		//检索实体对象列表中的每个实体对象
		for (Software software : list) {
			//将实体对象转换为数据传输对象
			SoftwareDto softwareDto=(SoftwareDto) DtoHelper.build(SoftwareDto.class, software);
			//获取实体对象属性type(类型为SoftwareType)
			SoftwareType type=software.getType();
			//设置数据传输对象的属性值
			if(type!=null){
				softwareDto.setTypeId(type.getId());
				softwareDto.setTypeName(type.getName());
			}
			//将数据传输对象加入到数据传输对象列表
			dtoList.add(softwareDto);
		}
		return dtoList;
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * @param id
	 * @return SoftwareDto
	 * 
	 */
	public SoftwareDto query(Integer id) throws Exception {
		Software software = Software.query(id);
		SoftwareDto dto = (SoftwareDto) DtoHelper.build(SoftwareDto.class, software);
		return dto;
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据软件类型Id查询
	 * @param typeId
	 * @return List<SoftwareDto>
	 */		
	public List<SoftwareDto> getSoftwares(String code) throws Exception{
		//获取所有实体对象
		List<Software> list = Software.getSoftwares(code);
		//定义数据传输对象列表
		List<SoftwareDto> dtoList = new ArrayList<SoftwareDto>();
		for (Software software : list) {
			//将实体对象转换为数据传输对象
			SoftwareDto softwareDto=(SoftwareDto) DtoHelper.build(SoftwareDto.class, software);
			//将数据传输对象加入到数据传输对象列表
			dtoList.add(softwareDto);
		}
		return dtoList;
	}
	
	
	
}
