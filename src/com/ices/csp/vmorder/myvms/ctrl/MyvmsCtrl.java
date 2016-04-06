package com.ices.csp.vmorder.myvms.ctrl;

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

import com.ices.csp.vmorder.myvms.dto.MonitorDto;
import com.ices.csp.vmorder.myvms.dto.MyvmsDto;
import com.ices.csp.vmorder.myvms.service.MyvmsService;

@Path(value = "myvms")
@Component
public class MyvmsCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private MyvmsService myvmsService;

	/*-----------------------业务逻辑-------------------------*/
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "myvmsMonitor")
	public Map<String, Object> myvmsMonitor(@QueryParam(value = "vmitemMark") Integer vmitemMark) throws Exception {
		List<MonitorDto> resultList = this.myvmsService.myvmsMonitor(vmitemMark);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("monitorList", resultList);
		return map;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "startVMItem")
	public Map<String, String> startVMItem(@QueryParam(value = "myvmid")Integer myvmid) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			int a = myvmsService.startVMItem(myvmid);
			if(a==1){
				result.put("status", "success");
			}else if(a==0){
				result.put("status", "ison");
			}else if(a==2){
				result.put("status", "noexist");
			}else{
				result.put("status", "error");
			}	
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "shutdownVMItem")
	public Map<String, String> shutdownVMItem(@QueryParam(value = "myvmid")Integer myvmid) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			int a = myvmsService.shutdownVMItem(myvmid);
			if(a==1){
				result.put("status", "success");
			}else if(a==0){
				result.put("status", "isdown");
			}else if(a==2){
				result.put("status", "noexist");
			}else{
				result.put("status", "error");
			}	
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "updateVMItem")
	public Map<String, String> updateVMItem(MyvmsDto myvmsdto) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			myvmsdto.setNodeName(myvmsdto.getNode());
			myvmsService.updateVMItem(myvmsdto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllMyvms")
	public Map<String, Object> findAllMyvms(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<MyvmsDto> resultList = this.myvmsService.findAllMyvms(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myvmsList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllFinalMyvms")
	public Map<String, Object> findAllFinalMyvms(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<MyvmsDto> resultList = this.myvmsService.findAllFinalMyvms(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myvmsList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchMyvms")
	public Map<String, Object> searchMyvms(Map<String, String> data) throws Exception {
		List<MyvmsDto> resultList = this.myvmsService.searchMyvms(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myvmsList", resultList);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchFinalMyvms")
	public Map<String, Object> searchFinalMyvms(Map<String, String> data) throws Exception {
		List<MyvmsDto> resultList = this.myvmsService.searchFinalMyvms(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myvmsList", resultList);
		return map;
	}
}
