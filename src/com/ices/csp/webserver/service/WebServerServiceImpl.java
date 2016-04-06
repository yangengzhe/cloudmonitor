/**
 * 
 */
package com.ices.csp.webserver.service;

import java.util.ArrayList;
import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.csp.software.domain.Software;
import com.ices.csp.webserver.domain.WebServer;
import com.ices.csp.webserver.dto.WebServerConditionDto;
import com.ices.csp.webserver.dto.WebServerDto;



/**
 * @author MFC
 *
 */
@Service
public class WebServerServiceImpl implements WebServerService{
	/*
	 * 新增操作
	 * @param webserverDto
	 * 
	 */
	public void create(WebServerDto webServerDto) throws DtoException{
		//将数据传输对象转换为实体对象
		WebServer webServer = (WebServer) DtoHelper.dismantle(WebServer.class, webServerDto);
		if(webServerDto.getSoftwareId()!=null){//软件类型不为空
			//根据id获取类型对象
			Software type=Software.query(webServerDto.getSoftwareId());
			webServer.setType(type);
		}
		WebServer.create(webServer);
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 修改操作
	 * @param webServerDto
	 */
	public void update(WebServerDto webServerDto) throws DtoException {
		WebServer webServer = WebServer.query(webServerDto.getId());
		WebServer dtoWebServer = (WebServer) DtoHelper.dismantle(WebServer.class, webServerDto);
		
		webServer.setName(dtoWebServer.getName());
		if(webServerDto.getSoftwareId()!=null){
			Software type=Software.query(webServerDto.getSoftwareId());
			webServer.setType(type);
		}
		webServer.setState(dtoWebServer.getState());
		webServer.setIp(dtoWebServer.getIp());
		webServer.setPort(dtoWebServer.getPort());
		webServer.setWebapps(dtoWebServer.getWebapps());
		webServer.setStorage(dtoWebServer.getStorage());
		webServer.setConnectionTimeout(dtoWebServer.getConnectionTimeout());
		webServer.setProtocol(dtoWebServer.getProtocol());
		webServer.setMaxThreads(dtoWebServer.getMaxThreads());
		webServer.setMinSpareThreads(dtoWebServer.getMinSpareThreads());
		webServer.setMaxSpareThreads(dtoWebServer.getMaxSpareThreads());
		webServer.setAcceptCount(dtoWebServer.getAcceptCount());
		webServer.setDemo(dtoWebServer.getDemo());
				
		WebServer.update(webServer);
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 删除操作
	 * @param id
	 */
	public void delete(Integer id) throws BusinessException {
		WebServer webServer = null;
		webServer = WebServer.query(id);
		WebServer.delete(webServer);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * 根据条件查询
	 * @param dto
	 * @param pb
	 * @return List<WebServerDto>
	 * 
	 */
	public List<WebServerDto> query(WebServerConditionDto dto, PagingBean pb) throws Exception {
		//根据查询条件获取实体对象列表
		List<WebServer> list = WebServer.query(dto, pb);
		//定义数据传输对象列表
		List<WebServerDto> dtoList = new ArrayList<WebServerDto>();
		//检索实体对象列表中的每个实体对象
		for (WebServer webServer : list) {
			//将实体对象转换为数据传输对象
			WebServerDto webServerDto=(WebServerDto) DtoHelper.build(WebServerDto.class, webServer);
			//获取实体对象属性type(类型为Software)
			Software type=webServer.getType();
			//设置数据传输对象的属性值
			if(type!=null){
				webServerDto.setSoftwareId(type.getId());
				webServerDto.setSoftwareName(type.getName());
			}
			//将数据传输对象加入到数据传输对象列表
			dtoList.add(webServerDto);
		}
		return dtoList;
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询操作
	 * @param id
	 * @return WebServerDto
	 * 
	 */
	public WebServerDto query(Integer id) throws Exception {
		WebServer webServer = WebServer.query(id);
		WebServerDto dto = (WebServerDto) DtoHelper.build(WebServerDto.class, webServer);
		return dto;
	}
	
}
