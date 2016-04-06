package com.ices.csp.vmorder.myorders.ctrl;

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

import com.ices.csp.vmorder.myorders.dto.MyitemsDto;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;
import com.ices.csp.vmorder.myorders.service.MyordersService;

@Path(value = "myorders")
@Component
public class MyordersCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private MyordersService myordersService;

	/*-----------------------业务逻辑-------------------------*/
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findItemsByOrderId")
	public Map<String, Object> findItemsByOrderId(@QueryParam(value = "orderid") Integer orderid) throws Exception {
		List<MyitemsDto> resultList = this.myordersService.findItemsByOrderId(orderid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemsList", resultList);
		return map;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllMyorders")
	public Map<String, Object> findAllMyorders(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<MyordersDto> resultList = this.myordersService.findAllMyorders(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myordersList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchMyorders")
	public Map<String, Object> searchMyorders(Map<String, String> data) throws Exception {
		List<MyordersDto> resultList = this.myordersService.searchMyorders(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myordersList", resultList);
		return map;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllFinalMyorders")
	public Map<String, Object> findAllFinalMyorders(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<MyordersDto> resultList = this.myordersService.findAllFinalMyorders(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myordersList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchFinalorders")
	public Map<String, Object> searchFinalorders(Map<String, String> data) throws Exception {
		List<MyordersDto> resultList = this.myordersService.searchFinalorders(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myordersList", resultList);
		return map;
	}
}
