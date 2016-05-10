package com.ices.cmp.app.evaluate.ctrl;

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

import com.ices.cmp.app.evaluate.dto.EvaluateDto;
import com.ices.cmp.app.evaluate.service.EvaluateService;
@Path(value = "evaluate")
@Component
public class EvaluateCtrl {
    @Autowired
    private EvaluateService evaluateService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllStatus")
    public Map<String, Object> findAllStatus(@QueryParam(value = "id") Integer id) throws Exception {
        List<EvaluateDto> resultList = this.evaluateService.findAllStatus(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataList", resultList);
        return map;
    }
}
