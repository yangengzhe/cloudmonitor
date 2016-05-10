package com.ices.cmp.topology.vms.ctrl;

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
import com.ices.cmp.topology.server.dto.TopoServerDto;
import com.ices.cmp.topology.server.service.TopoServer;
import com.ices.cmp.topology.vms.service.TopoVmsService;
import com.ices.cmp.vms.myvms.service.VmsService;

@Path(value = "topo_vms")
@Component
public class TopoVmsCtrl {
    @Autowired
    private TopoVmsService topoVmsService;
    
    @Autowired
    private VmsService vmsService;
    
    @Autowired
    private ServerService serverService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "getTopoServer")
    public Map<String, Object> getTopoServer(@QueryParam(value = "id") Integer id) throws Exception {
        List<TopoServerDto> resultList = this.topoVmsService.getServerTopo(vmsService.findVmsById(id));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", resultList.size());
        map.put("detal", resultList);
        return map;
    }
}
