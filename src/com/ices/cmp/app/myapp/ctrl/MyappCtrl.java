package com.ices.cmp.app.myapp.ctrl;

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

import com.ices.cmp.app.myapp.dto.MyappDto;
import com.ices.cmp.app.myapp.service.MyappService;

@Path(value = "my_app")
@Component
public class MyappCtrl {
    @Autowired
    private MyappService myappService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findMyappByName")
    public List<MyappDto> findMyappByName(@QueryParam(value = "myappName") String myappName) throws Exception {
        List<MyappDto> resultList = this.myappService.findMyappByName(myappName);
//      Map<String, Object> map = new HashMap<String, Object>();
//      map.put("serverList", resultList);
//      //map.put("pagingBean",pb);
        return resultList;
    }
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllMyapp")
    public Map<String, Object> findAllMyapp(@QueryParam(value = "paging") PagingBean pb) throws Exception {
        List<MyappDto> resultList = this.myappService.findAllMyapp(pb);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appList", resultList);
        map.put("pagingBean",pb);
        return map;
    }
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "deleteMyapp")
    public void deleteMyapp(String deleteData) throws Exception {
        String myappId[] = deleteData.split(",");
        int length = deleteData.split(",").length;
        int deleteMyapp[] = new int[length];
        for (int i = 0; i < length; i++) {
            deleteMyapp[i] = Integer.parseInt(myappId[i]);
        }
        myappService.deleteMyapp(deleteMyapp);
    }
    @PUT
    @Produces({ "application/json" })
    @Path(value = "addMyapp")
    public Map<String, Object> addMyapp(MyappDto myappDto) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            myappService.addMyapp(myappDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "updateMyapp")
    public Map<String, String> updateMyapp(MyappDto myappDto) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        try {
            myappService.updateMyapp(myappDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "searchMyapp")
    public Map<String, Object> searchMyapp(Map<String, String> data) throws Exception {
        List<MyappDto> resultList = this.myappService.findByParam(data);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appList", resultList);
        return map;
    }
}
