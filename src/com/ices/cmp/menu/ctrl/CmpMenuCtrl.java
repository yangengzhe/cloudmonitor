package com.ices.cmp.menu.ctrl;

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

import com.ices.cmp.menu.dto.CmpMenuDto;
import com.ices.cmp.menu.service.CmpMenuService;
import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.node.dto.NodeDto;

@Path(value = "cmp_menu")
@Component
public class CmpMenuCtrl {
    @Autowired
    private CmpMenuService menuService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "getMenuChildren")
    public List<CmpMenuDto> getMenuChildren(@QueryParam(value = "id") Integer id) throws Exception {
        List<CmpMenuDto> list=null;
        if(id==0)
            list = menuService.findAllNode();
        else
            list = menuService.findServerBynodeId(id);
        return list;
    }
}
