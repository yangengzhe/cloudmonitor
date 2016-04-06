package com.ices.csp.os.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.csp.os.dto.OSDto;
import com.ices.csp.os.dto.OSVersionDto;
import com.ices.csp.os.service.OSService;

@Path(value = "os")
@Component
public class OSCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private OSService osService;

	/*-----------------------业务逻辑-------------------------*/
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findOSVersionByosname")
	public Map<String, Object> findOSVersionByosname(@QueryParam(value = "osname")String osname) throws Exception {
		List<OSVersionDto> resultList = this.osService.findOSVersionByosname(osname);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osversionList", resultList);
		return map;
	}
	
	@GET
	@Produces({ "application/json" })
	@Path("findDuplicate")
	@RolesAllowed({ "ROLE_ADMIN", "ROLE_SYS_ADMIN",  })
	public boolean findDuplicate(@QueryParam("osversionCode")String osversionCode) throws DtoException{

		if(osService.findDuplicate(osversionCode))
		{
			return true;//查询到有相同版本编码
		}
		else
		{
			return false;//没有查到相同编码
		}
		
		

	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllOS")
	public Map<String, Object> findAllOS(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<OSDto> resultList = this.osService.findAllOS(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findOSVersion")
	public Map<String, Object> findOSVersion(@QueryParam(value = "id")Integer id,@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<OSVersionDto> resultList = this.osService.findOSVersion(id,pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osversionList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "deleteOSVersion")
	public void deleteOSVersion(String deleteData) throws Exception {
		String osversionId[] = deleteData.split(",");
		int length = deleteData.split(",").length;
		int deleteosversion[] = new int[length];
		for (int i = 0; i < length; i++) {
			deleteosversion[i] = Integer.parseInt(osversionId[i]);
		}
		osService.deleteOSVersion(deleteosversion);
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "deleteOS")
	public void deleteOS(String deleteData) throws Exception {
		String osId[] = deleteData.split(",");
		int length = deleteData.split(",").length;
		int deleteos[] = new int[length];
		for (int i = 0; i < length; i++) {
			deleteos[i] = Integer.parseInt(osId[i]);
		}
		osService.deleteOS(deleteos);
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchOS")
	public Map<String, Object> searchOS(Map<String, String> data) throws Exception {
		List<OSDto> resultList = this.osService.searchOS(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osList", resultList);
		return map;
	}

	@PUT
	@Produces({ "application/json" })
	@Path(value = "addOS")
	public Map<String, Object> addOS(OSDto osDto) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			osService.addOS(osDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "updateOS")
	public Map<String, String> updateOS(OSDto osDto) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			osService.updateOS(osDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@PUT
	@Produces({ "application/json" })
	@Path(value = "addOSVersion")
	public Map<String, Object> addOSVersion(OSVersionDto osversionDto) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			osService.addOSVersion(osversionDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "updateOSVersion")
	public Map<String, String> updateOSVersion(OSVersionDto osversionDto) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			osService.updateOSVersion(osversionDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
}
