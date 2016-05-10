package com.ices.cmp.app.status.ctrl;

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

import com.ices.cmp.app.myapp.dto.MyappDto;
import com.ices.cmp.app.myapp.service.MyappService;
import com.ices.cmp.app.status.dto.AppstatusDto;
import com.ices.cmp.app.status.service.AppstatusService;
import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.myserver.service.ServerService;
import com.ices.cmp.server.status.dto.StatusDto;
import com.ices.cmp.server.status.service.StatusService;
@Path(value = "appstatus")
@Component
public class AppstatusCtrl {
    @Autowired
    private AppstatusService appstatusService;
    @Autowired
    private MyappService myappService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllStatus")
    public Map<String, Object> findAllStatus(@QueryParam(value = "id") Integer id) throws Exception {
        List<AppstatusDto> resultList = this.appstatusService.findAllStatus(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataList", resultList);
        return map;
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "appDetail")
    public Map<String, Object> appDetail(@QueryParam(value = "id") Integer id) throws Exception {
        MyappDto myapp = myappService.findMyappById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("name", myapp.getName());
        map.put("status", myapp.getState());
        map.put("area", myapp.getCode());
        map.put("gmt_create", myapp.getVmCode());
        map.put("desc", myapp.getDesc());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", map);
        result.put("success", true);
        return result;
    }
}
