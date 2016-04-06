package com.ices.csp.os.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;


import com.ices.csp.os.domain.OS;
import com.ices.csp.os.domain.OSVersion;
import com.ices.csp.os.dto.OSDto;
import com.ices.csp.os.dto.OSVersionDto;

@Service
public class OSServiceImpl implements OSService{
	
	//根据osname查询所包含版本
	@Override
	public List<OSVersionDto> findOSVersionByosname(String osname) throws DtoException {
		List<OSVersionDto> resultList = new ArrayList<OSVersionDto>();
		List<OSVersion> result = OSVersion.findOSVersionByosname(osname);
		for (OSVersion osversion : result) {
			OSVersionDto osversionDto = DtoHelper.build(OSVersionDto.class, osversion);
			resultList.add(osversionDto);
		}
		return resultList;
	}
	//查询版本编号是否存在
	@Override
	public boolean findDuplicate(String osversionCode) throws DtoException {
		List<OSVersion> result = OSVersion.findByDuplicate(null,osversionCode);
		if(result.size()==0)
		{
			return false;//查重失败
		}
		else
		{
			return true;//查询到有相同功能编码
		}
	}
	// 删除操作系统版本信息
	@Override
	public void deleteOSVersion(int[] deleteOSVersion) {
		for (int i = 0; i < deleteOSVersion.length; i++) {
			OSVersion osversion = OSVersion.findById(deleteOSVersion[i]);
			osversion.delete();
		}

	}
	// 删除操作系统
	@Override
	public void deleteOS(int[] deleteOS) {
		for (int i = 0; i < deleteOS.length; i++) {
			OS os = OS.findById(deleteOS[i]);
			os.delete();
		}

	}
	// 查询所有数据表中已定义的操作系统
	@Override
	public List<OSDto> findAllOS(PagingBean pb) throws DtoException {
		List<OSDto> resultList = new ArrayList<OSDto>();
		List<OS> result = OS.findAll(pb);
		for (OS os : result) {
			OSDto osDto = DtoHelper.build(OSDto.class, os);
			resultList.add(osDto);
		}
		return resultList;
	}
	//根据操作系统id查询所包含版本
	@Override
	public List<OSVersionDto> findOSVersion(Integer id,PagingBean pb) throws DtoException {
		List<OSVersionDto> resultList = new ArrayList<OSVersionDto>();
		List<OSVersion> result = OSVersion.findAllByOSId(id,pb);
		for (OSVersion osversion : result) {
			OSVersionDto osversionDto = DtoHelper.build(OSVersionDto.class, osversion);
			resultList.add(osversionDto);
		}
		return resultList;
	}
	// 根据操作系统名称检索
	@Override
	public List<OSDto> searchOS(Map<String, String> data) throws DtoException {
		List<OSDto> resultList = new ArrayList<OSDto>();
		List<OS> result = OS.findByName(data.get("osName"));
		for (OS os : result) {
			OSDto osDto = DtoHelper.build(OSDto.class, os);
			resultList.add(osDto);
		}
		return resultList;
	}
	// 增加新定义的操作系统
	@Override
	public void addOS(OSDto osDto) throws Exception {
		List<OS> result = OS.findByDuplicate(null,osDto.getCode());
		if (result != null && result.size() > 0) {
			throw new BusinessException("数据库中已经存在相同的操作系统编码！");
		}
		OS os = DtoHelper.dismantle(OS.class, osDto);
		os.insert();
		List<OSVersionDto> result2 = osDto.getVeisions();
		for (OSVersionDto osversionDto : result2) {
			OSVersion osversion = new OSVersion();
			osversion.setCode(osversionDto.getCode());
			osversion.setDesc(osversionDto.getDesc());
			osversion.setName(osversionDto.getName());
			osversion.setOs(os);
			osversion.insert();
		}
	}
	//更新修改操作系统信息
	@Override
	public void updateOS(OSDto osDto) throws Exception {
		List<OS> result = OS.findByDuplicate(osDto.getId(),osDto.getCode());
		if (result != null && result.size() > 0) {
			System.out.println("出错了！" + osDto.getCode());
			throw new BusinessException("数据库中已经存在相同的操作系统编码！");
		}
		OS os = OS.findById(osDto.getId());
		DtoHelper.dismantle(os, osDto);
	}
	// 增加新定义的操作系统版本
	@Override
	public void addOSVersion(OSVersionDto osversionDto) throws Exception {
		List<OSVersion> result = OSVersion.findByDuplicate(null,osversionDto.getCode());
		if (result != null && result.size() > 0) {
			throw new BusinessException("数据库中已经存在相同的版本编码！");
		}
		OSVersion osversion = DtoHelper.dismantle(OSVersion.class, osversionDto);
		if(osversionDto.getOsId()!=null){
			osversion.setOs(OS.findById(osversionDto.getOsId()));
		}
		osversion.insert();
	}
	//更新修改版本信息
	@Override
	public void updateOSVersion(OSVersionDto osversionDto) throws Exception {
		List<OSVersion> result = OSVersion.findByDuplicate(osversionDto.getId(),osversionDto.getCode());
		if (result != null && result.size() > 0) {
			System.out.println("出错了！" + osversionDto.getCode());
			throw new BusinessException("数据库中已经存在相同的版本编码！");
		}
		OSVersion osversion = OSVersion.findById(osversionDto.getId());
		DtoHelper.dismantle(osversion, osversionDto);
	}
}
