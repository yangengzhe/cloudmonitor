package com.ices.cmp.app.evaluate.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.cspframework.common.PagingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.cmp.app.evaluate.dto.AlertDto;
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
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllAlert")
    public Map<String, Object> findAllAlert(@QueryParam(value = "paging") PagingBean pb) throws Exception {
        List<AlertDto> resultList = this.evaluateService.findAllAlert(pb);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alertList", resultList);
        map.put("pagingBean",pb);
        return map;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "deleteAlert")
    public void deleteAlert(String deleteData) throws Exception {
        String serverId[] = deleteData.split(",");
        int length = deleteData.split(",").length;
        int deleteServer[] = new int[length];
        for (int i = 0; i < length; i++) {
            deleteServer[i] = Integer.parseInt(serverId[i]);
        }
        evaluateService.deleteAlert(deleteServer);
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "updateAlert")
    public void updateAlert(String deleteData) throws Exception {
        String serverId[] = deleteData.split(",");
        int length = deleteData.split(",").length;
        int deleteServer[] = new int[length];
        for (int i = 0; i < length; i++) {
            deleteServer[i] = Integer.parseInt(serverId[i]);
        }
        evaluateService.updateAlert(deleteServer);
    }
}
