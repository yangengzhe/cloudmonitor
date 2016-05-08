package com.ices.cmp.server.route.ctrl;

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

import com.ices.cmp.server.route.dto.RouteDto;
import com.ices.cmp.server.route.service.RouteService;

@Path(value = "route")
@Component
public class RouteCtrl {
    /*---------------------类成员定义-------------------------*/
    @Autowired
    private RouteService routeService;
    
    /*-----------------------业务逻辑-------------------------*/
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllRoute")
    public Map<String, Object> findAllRoute(@QueryParam(value = "paging") PagingBean pb,@QueryParam(value = "nodeid") Integer nodeid) throws Exception {
        List<RouteDto> resultList = this.routeService.findAllRoute(pb,nodeid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("routeList", resultList);
        map.put("pagingBean",pb);
        return map;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "deleteRoute")
    public void deleteRoute(String deleteData) throws Exception {
        String routeId[] = deleteData.split(",");
        int length = deleteData.split(",").length;
        int deleteRoute[] = new int[length];
        for (int i = 0; i < length; i++) {
            deleteRoute[i] = Integer.parseInt(routeId[i]);
        }
        routeService.deleteRoute(deleteRoute);
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "searchRoute")
    public Map<String, Object> searchRoute(Map<String, String> data) throws Exception {
        List<RouteDto> resultList = this.routeService.searchRoute(data);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("routeList", resultList);
        return map;
    }

    @PUT
    @Produces({ "application/json" })
    @Path(value = "addRoute")
    public Map<String, Object> addRoute(RouteDto routeDto) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            routeService.addRoute(routeDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "updateRoute")
    public Map<String, String> updateRoute(RouteDto routeDto) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        try {
            routeService.updateRoute(routeDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }
}
