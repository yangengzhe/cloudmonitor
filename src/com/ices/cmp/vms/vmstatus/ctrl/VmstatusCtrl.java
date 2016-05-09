package com.ices.cmp.vms.vmstatus.ctrl;

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
import com.ices.cmp.vms.myvms.domain.Vms;
import com.ices.cmp.vms.myvms.dto.VmsDto;
import com.ices.cmp.vms.myvms.service.VmsService;
import com.ices.cmp.vms.vmstatus.dto.VmstatusDto;
import com.ices.cmp.vms.vmstatus.service.VmstatusService;

@Path(value = "vmstatus")
@Component
public class VmstatusCtrl {
    @Autowired
    private VmstatusService vmstatusService;
    @Autowired
    private VmsService vmsService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllVms")
    public Map<String, Object> findAllVms(@QueryParam(value = "id") Integer id) throws Exception {
        List<VmstatusDto> resultList = this.vmstatusService.findAllVmstatus(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vmsList", resultList);
        return map;
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "vmDetail")
    public Map<String, Object> vmDetail(@QueryParam(value = "id") Integer id) throws Exception {
        VmsDto vmsDto = vmsService.findVmsById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("name", vmsDto.getName());
        map.put("status", vmsDto.getState());
        map.put("area", vmsDto.getServerCode());
        map.put("gmt_create", vmsDto.getCode());
        map.put("desc", vmsDto.getDesc());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", map);
        result.put("success", true);
        return result;
    }
}
