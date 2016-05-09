package com.ices.cmp.vms.myvms.ctrl;

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

import com.ices.cmp.vms.myvms.dto.VmsDto;
import com.ices.cmp.vms.myvms.service.VmsService;

@Path(value = "my_vms")
@Component
public class VmsCtrl {
    @Autowired
    private VmsService vmsService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findVmsByName")
    public List<VmsDto> findVmsByName(@QueryParam(value = "vmsName") String vmsName) throws Exception {
        List<VmsDto> resultList = this.vmsService.findVmsByName(vmsName);
//      Map<String, Object> map = new HashMap<String, Object>();
//      map.put("serverList", resultList);
//      //map.put("pagingBean",pb);
        return resultList;
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllVms")
    public Map<String, Object> findAllVms(@QueryParam(value = "paging") PagingBean pb) throws Exception {
        List<VmsDto> resultList = this.vmsService.findAllVms(pb);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vmsList", resultList);
        map.put("pagingBean",pb);
        return map;
    }
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "deleteVms")
    public void deleteVms(String deleteData) throws Exception {
        String vmsId[] = deleteData.split(",");
        int length = deleteData.split(",").length;
        int deleteVms[] = new int[length];
        for (int i = 0; i < length; i++) {
            deleteVms[i] = Integer.parseInt(vmsId[i]);
        }
        vmsService.deleteVms(deleteVms);
    }
    @PUT
    @Produces({ "application/json" })
    @Path(value = "addVms")
    public Map<String, Object> addVms(VmsDto vmsDto) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            vmsService.addVms(vmsDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "updateVms")
    public Map<String, String> updateVms(VmsDto vmsDto) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        try {
            vmsService.updateVms(vmsDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "searchVms")
    public Map<String, Object> searchVms(Map<String, String> data) throws Exception {
        List<VmsDto> resultList = this.vmsService.findByParam(data);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vmsList", resultList);
        return map;
    }
}
