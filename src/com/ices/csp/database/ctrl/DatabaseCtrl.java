/**
 * 
 */
package com.ices.csp.database.ctrl;

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


import com.ices.csp.database.dto.DatabaseConditionDto;
import com.ices.csp.database.dto.DatabaseDto;
import com.ices.csp.database.service.DatabaseService;
/**
 * @author MFC
 *
 */
@Path("database")
@Component
public class DatabaseCtrl {
	@Autowired
	DatabaseService databaseService;
	//////////////////////////////////////////////////////////////////////////////
	/*
	 * 查询对象
	 * 
	 */
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("query")
	public Map<String, Object> query(@QueryParam("paging") PagingBean pb, @QueryParam("condition") DatabaseConditionDto dto) 
			throws Exception{
		List list = this.databaseService.query(dto, pb);
		Map map = new HashMap();
		map.put("databaseList", list);
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
	public Map<String, Object> add(DatabaseDto databaseDto)
			throws Exception{
		Map result = new HashMap();
		this.databaseService.create(databaseDto);
		result.put("success", Boolean.valueOf(true));
		result.put("message", "Add Database");
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
		DatabaseDto dto = this.databaseService.query(intId);
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
	public Map<String, Object> upadte(DatabaseDto databaseDto, @PathParam("id") String id)
			throws Exception{
		Map result = new HashMap();
		this.databaseService.update(databaseDto);
		result.put("success", Boolean.valueOf(true));
		result.put("message", "update database");
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
					this.databaseService.delete(intId);
				}
		}
	}

}
