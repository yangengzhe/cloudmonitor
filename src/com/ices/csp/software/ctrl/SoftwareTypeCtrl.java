/**
 * 
 */
package com.ices.csp.software.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;
import org.cspframework.common.PagingBean;
import org.cspframework.manage.role.dto.RoleDto;

import com.ices.csp.software.dto.SoftwareTypeCondition;
import com.ices.csp.software.dto.SoftwareTypeDto;
import com.ices.csp.software.service.SoftwareTypeService;






/**
 * @author Fanchao Meng
 *
 */
@Path("softwaretype")
@Component
public class SoftwareTypeCtrl {
	@Autowired
	SoftwareTypeService  softwareTypeServer;
	
	////////////////////////////////////////////////////////////////////////
	/*
	 * 查询对象
	 * 
	 */
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("query")
	public Map<String, Object> query(@QueryParam("paging") PagingBean pb, @QueryParam("condition") SoftwareTypeCondition dto) 
			throws Exception{
		List list = this.softwareTypeServer.query1(dto, pb);
		Map map = new HashMap();
		map.put("softwareTypeList", list);
		map.put("pagingBean", pb);
		return map;
	}
	////////////////////////////////////////////////////////////////////////////
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("getSoftwareTypes")
	public List<SoftwareTypeDto> getSoftwareTypes()throws Exception{
		List<SoftwareTypeDto> results=this.softwareTypeServer.getSoftwareTypes();
		return results;
	}
	
	//////////////////////////////////////////////////////////////////////////
	/*
	 * 增加对象
	 */
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("add")
	public Map<String, Object> add(SoftwareTypeDto softwareTypeDto)
			throws Exception{
		Map result = new HashMap();
		this.softwareTypeServer.create(softwareTypeDto);
		result.put("success", Boolean.valueOf(true));
		result.put("message", "Add SoftwareType");
		return result;
	}
    //////////////////////////////////////////////////////////////////////////
	/*
	 * 更新对象
	 * 
	 */
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("update")
	public Map<String, Object> upadte(SoftwareTypeDto softwareTypeDto, @PathParam("id") String id)
			throws Exception{
		Map result = new HashMap();
		this.softwareTypeServer.update(softwareTypeDto);
		result.put("success", Boolean.valueOf(true));
		result.put("message", "update SoftwareType");
		return result;
	}
	/////////////////////////////////////////////////////////////////////////
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
		SoftwareTypeDto dto = this.softwareTypeServer.query(intId);
		Map result = new HashMap();
		result.put("data", dto);
		result.put("success", Boolean.valueOf(true));
		return result;
	}
	/////////////////////////////////////////////////////////////////////////////
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
					this.softwareTypeServer.delete(intId);
				}
		}
	}
	
	
}
