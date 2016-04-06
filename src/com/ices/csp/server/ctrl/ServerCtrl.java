package com.ices.csp.server.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.cspframework.common.PagingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.csp.server.dto.ServerDto;
import com.ices.csp.server.service.ServerService;

@Path(value = "server")
@Component
public class ServerCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private ServerService serverService;

	/*-----------------------业务逻辑-------------------------*/
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findServerBynodeName")
	public List<ServerDto> findServerBynodeName(@QueryParam(value = "nodeName") String nodeName) throws Exception {
		List<ServerDto> resultList = this.serverService.findServerBynodeName(nodeName);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("serverList", resultList);
//		//map.put("pagingBean",pb);
		return resultList;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllServer")
	public Map<String, Object> findAllServer(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<ServerDto> resultList = this.serverService.findAllServer(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serverList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "deleteServer")
	public void deleteServer(String deleteData) throws Exception {
		String serverId[] = deleteData.split(",");
		int length = deleteData.split(",").length;
		int deleteServer[] = new int[length];
		for (int i = 0; i < length; i++) {
			deleteServer[i] = Integer.parseInt(serverId[i]);
		}
		serverService.deleteServer(deleteServer);
	}
	@PUT
	@Produces({ "application/json" })
	@Path(value = "addServer")
	public Map<String, Object> addServer(ServerDto serverDto) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			serverService.addServer(serverDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "updateServer")
	public Map<String, String> updateServer(ServerDto serverDto) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		try {
			serverService.updateServer(serverDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchServer")
	public Map<String, Object> searchServer(Map<String, String> data) throws Exception {
		List<ServerDto> resultList = this.serverService.findByParam(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serverList", resultList);
		return map;
	}
}
