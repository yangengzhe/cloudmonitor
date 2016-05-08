package com.ices.cmp.server.usage.ctrl;

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

import com.ices.cmp.server.status.dto.StatusDto;
import com.ices.cmp.server.usage.dto.UsageDto;
import com.ices.cmp.server.usage.service.UsageService;

@Path(value = "usage")
@Component
public class UsageCtrl {
    @Autowired
    private UsageService usageService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllUsage")
    public Map<String, Object> findAllUsage(@QueryParam(value = "id") Integer id) throws Exception {
        List<UsageDto> resultList = this.usageService.findAllUsage(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataList", resultList);
        return map;
    }
}
