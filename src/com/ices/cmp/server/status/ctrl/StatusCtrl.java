package com.ices.cmp.server.status.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.myserver.service.ServerService;
import com.ices.cmp.server.status.dto.StatusDto;
import com.ices.cmp.server.status.service.StatusService;

@Path(value = "status")
@Component
public class StatusCtrl {
    @Autowired
    private StatusService statusService;
    @Autowired
    private ServerService serverService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllStatus")
    public Map<String, Object> findAllStatus(@QueryParam(value = "id") Integer id) throws Exception {
        List<StatusDto> resultList = this.statusService.findAllStatus(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("statusList", resultList);
        return map;
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "serverDetail")
    public Map<String, Object> serverDetail(@QueryParam(value = "id") Integer id) throws Exception {
        ServerDto serverDto = serverService.findServerById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("name", serverDto.getName());
        map.put("status", serverDto.getState());
        map.put("area", serverDto.getNodeName());
        map.put("gmt_create", serverDto.getVplatform());
        map.put("desc", serverDto.getDesc());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", map);
        result.put("success", true);
        return result;
    }
}
