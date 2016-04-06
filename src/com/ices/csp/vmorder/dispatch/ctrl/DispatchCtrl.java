package com.ices.csp.vmorder.dispatch.ctrl;

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

import com.ices.csp.vmorder.dispatch.service.DispatchService;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;

@Path(value = "dispatch")
@Component
public class DispatchCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private DispatchService dispatchService;

	/*-----------------------业务逻辑-------------------------*/
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "changestateDispatch")
	public Map<String, String> changestateDispatch(MyordersDto myordersDto) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			dispatchService.changestateDispatch(myordersDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "changestateCancel")
	public Map<String, String> changestateCancel(String cancelData) throws Exception {
		String orderId[] = cancelData.split(",");
		int length = cancelData.split(",").length;
		int canceldispatch[] = new int[length];
		for (int i = 0; i < length; i++) {
			canceldispatch[i] = Integer.parseInt(orderId[i]);
		}
		Map<String, String> result = new HashMap<String, String>();
		try {
			dispatchService.changestateCancel(canceldispatch);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "deleteDispatch")
	public Map<String, String> deleteDispatch(String deleteData) throws Exception {
		String orderId[] = deleteData.split(",");
		int length = deleteData.split(",").length;
		int deletedispatch[] = new int[length];
		for (int i = 0; i < length; i++) {
			deletedispatch[i] = Integer.parseInt(orderId[i]);
		}
		Map<String, String> result = new HashMap<String, String>();
		try {
			dispatchService.deleteDispatch(deletedispatch);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findVMOrderForDispatch")
	public Map<String, Object> findVMOrderForDispatch(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<MyordersDto> resultList = this.dispatchService.findVMOrderForDispatch(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vmorderList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchDispatch")
	public Map<String, Object> searchDispatch(Map<String, String> data) throws Exception {
		List<MyordersDto> resultList = this.dispatchService.searchDispatch(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vmorderList", resultList);
		return map;
	}
}
