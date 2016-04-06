/**
 * 
 */
package com.ices.csp.software.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.cspframework.common.PagingBean;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.csp.software.dto.SoftwareConditionDto;
import com.ices.csp.software.dto.SoftwareDto;
import com.ices.csp.software.service.SoftwareService;



/**
 * @author Fanchao Meng
 *
 */
@Path("software")
@Component
public class SoftwareCtrl {
	@Autowired
	SoftwareService  softwareServer; 
	//////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询对象
	 * 
	 */
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("query")
	public Map<String, Object> query(@QueryParam("paging") PagingBean pb, @QueryParam("condition") SoftwareConditionDto dto) 
			throws Exception{
		List list = this.softwareServer.query(dto, pb);
		Map map = new HashMap();
		map.put("softwareList", list);
		map.put("pagingBean", pb);
		return map;
	}
	//////////////////////////////////////////////////////////////////////////////
	/*
	 * 增加对象
	 */
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("add")
	public Map<String, Object> add(SoftwareDto softwareDto)
			throws Exception{
		Map result = new HashMap();
		this.softwareServer.create(softwareDto);
		result.put("success", Boolean.valueOf(true));
		result.put("message", "Add Software");
		return result;
	}
	///////////////////////////////////////////////////////////////////////////////
	/*
	 * 根据id获取对象
	 * 
	 */
	@GET
	@Produces({"application/json"})
	@Path("update/{id}")
	public Map<String, Object> update(@PathParam("id") String id)
			throws Exception{
		Integer intId = null;
		if (StringUtils.isNumeric(id)) {
			intId = Integer.valueOf(Integer.parseInt(id));
		}
		SoftwareDto dto = this.softwareServer.query(intId);
		Map result = new HashMap();
		result.put("data", dto);
		result.put("success", Boolean.valueOf(true));
		return result;
	}
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * 更新对象
	 * 
	 */
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("update")
	public Map<String, Object> upadte(SoftwareDto softwareDto, @PathParam("id") String id)
			throws Exception{
		Map result = new HashMap();
		this.softwareServer.update(softwareDto);
		result.put("success", Boolean.valueOf(true));
		result.put("message", "update Software");
		return result;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	@DELETE
	@Produces({"application/json"})
	@Path("delete/{ids}")
	public void delete(@PathParam("ids") String ids)
			throws Exception{
		if (ids != null) {
			String[] idArr = ids.split(",");
			if (idArr != null)
				for (int i = 0; i < idArr.length; i++) {
					Integer intId = null;
					if (StringUtils.isNumeric(idArr[i])) {
						intId = Integer.valueOf(Integer.parseInt(idArr[i]));
					}
					this.softwareServer.delete(intId);
				}
		}
	}
	/////////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("getWebServers")
	public List<SoftwareDto> getWebServers()throws Exception{
		List<SoftwareDto> results=this.softwareServer.getSoftwares("WS");
		return results;
	}
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("getDatabases")
	public List<SoftwareDto> getDatabases()throws Exception{
		List<SoftwareDto> results=this.softwareServer.getSoftwares("DB");
		return results;
	}
	
	
}
