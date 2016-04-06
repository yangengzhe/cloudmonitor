package com.ices.csp.vmorder.audit.ctrl;

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

import com.ices.csp.vmorder.audit.service.AuditService;
import com.ices.csp.vmorder.myorders.dto.MyordersDto;

@Path(value = "audit")
@Component
public class AuditCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private AuditService auditService;

	/*-----------------------业务逻辑-------------------------*/
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "createvmitem")
	public void createvmitem(String createIds) throws Exception {
		String createId[] = createIds.split(",");
		int length = createIds.split(",").length;
		int vmsid[] = new int[length];
		for (int i = 0; i < length; i++) {
			vmsid[i] = Integer.parseInt(createId[i]);
		}
		auditService.createvmitem(vmsid);
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "allvmitemnocreate")
	public void allvmitemnocreate(@QueryParam(value = "vmid")Integer vmid) throws Exception {
		auditService.allvmitemnocreate(vmid);
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "changestateAudit")
	public Map<String, String> changestateAudit(@QueryParam(value = "orderid")Integer orderid) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			auditService.changestateAudit(orderid);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "commitAudit")
	public Map<String, String> commitAudit(MyordersDto myordersDto) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			auditService.commitAudit(myordersDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findVMOrderISApply")
	public Map<String, Object> findVMOrderISApply(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<MyordersDto> resultList = this.auditService.findVMOrderISApply(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vmorderList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchAudit")
	public Map<String, Object> searchAudit(Map<String, String> data) throws Exception {
		List<MyordersDto> resultList = this.auditService.searchAudit(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vmorderList", resultList);
		return map;
	}
}
