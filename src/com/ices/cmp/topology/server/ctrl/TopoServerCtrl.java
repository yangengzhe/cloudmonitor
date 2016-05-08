package com.ices.cmp.topology.server.ctrl;

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

@Path(value = "topo")
@Component
public class TopoServerCtrl {

    @Autowired
    private TopoServer topoServer;
    
    @Autowired
    private ServerService serverService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "getTopoServer")
    public Map<String, Object> getTopoServer(@QueryParam(value = "id") Integer id) throws Exception {
        ServerDto server = serverService.findServerById(id);
        List<TopoServerDto> resultList = this.topoServer.getServerTopo(server);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", resultList.size());
        map.put("detal", resultList);
        return map;
    }
}
