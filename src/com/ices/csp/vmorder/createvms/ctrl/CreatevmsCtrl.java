package com.ices.csp.vmorder.createvms.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.cspframework.common.PagingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.csp.vmorder.createvms.service.CreatevmsService;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;
import com.ices.csp.vmorder.myvms.dto.MyvmsDto;

@Path(value = "createvms")
@Component
public class CreatevmsCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private CreatevmsService createvmsService;

	/*-----------------------业务逻辑-------------------------*/
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "changestateCreatevms")
	public Map<String, String> changestateCreatevms(@QueryParam(value = "orderid")Integer orderid) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			createvmsService.changestateCreatevms(orderid);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "commitCreatevmsedit")
	public Map<String, String> commitCreatevmsedit(MyvmsDto myvmsdto) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			createvmsService.commitCreatevmsedit(myvmsdto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findVMOrderForCreatevms")
	public Map<String, Object> findVMOrderForCreatevms(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<MyordersDto> resultList = this.createvmsService.findVMOrderForCreatevms(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vmorderList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchCreatevms")
	public Map<String, Object> searchCreatevms(Map<String, String> data) throws Exception {
		List<MyordersDto> resultList = this.createvmsService.searchCreatevms(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vmorderList", resultList);
		return map;
	}
}
