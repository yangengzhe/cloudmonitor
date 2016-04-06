/**
 * 
 */
package com.ices.csp.webserver.ctrl;

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

import com.ices.csp.webserver.dto.WebServerConditionDto;
import com.ices.csp.webserver.dto.WebServerDto;
import com.ices.csp.webserver.service.WebServerService;

/**
 * @author MFC
 *
 */
@Path("webserver")
@Component
public class WebServerCtrl {
	@Autowired
	WebServerService webServerService;
	//////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询对象
	 * 
	 */
	@GET
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @Path("query")
    public Map<String, Object> query(@QueryParam("paging") PagingBean pb, @QueryParam("condition") WebServerConditionDto dto) 
            throws Exception{
        List list = this.webServerService.query(dto, pb);
        Map map = new HashMap();
        map.put("webServerList", list);
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
	public Map<String, Object> add(WebServerDto webServerDto)
			throws Exception{
		Map result = new HashMap();
		this.webServerService.create(webServerDto);
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
		WebServerDto dto = this.webServerService.query(intId);
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
	public Map<String, Object> upadte(WebServerDto webServerDto, @PathParam("id") String id)
			throws Exception{
		Map result = new HashMap();
		this.webServerService.update(webServerDto);
		result.put("success", Boolean.valueOf(true));
		result.put("message", "update webServer");
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
					this.webServerService.delete(intId);
				}
		}
	}

}
