package com.ices.cmp.server.node.ctrl;

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

import com.ices.cmp.server.node.dto.NodeDto;
import com.ices.cmp.server.node.service.NodeService;

@Path(value = "node")
@Component
public class NodeCtrl {
    /*---------------------类成员定义-------------------------*/
    @Autowired
    private NodeService nodeService;

    /*-----------------------业务逻辑-------------------------*/
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "findAllNode")
    public Map<String, Object> findAllNode(@QueryParam(value = "paging") PagingBean pb) throws Exception {
        List<NodeDto> resultList = this.nodeService.findAllNode(pb);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nodeList", resultList);
        map.put("pagingBean",pb);
        return map;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "deleteNode")
    public void deleteNode(String deleteData) throws Exception {
        String nodeId[] = deleteData.split(",");
        int length = deleteData.split(",").length;
        int deleteNode[] = new int[length];
        for (int i = 0; i < length; i++) {
            deleteNode[i] = Integer.parseInt(nodeId[i]);
        }
        nodeService.deleteNode(deleteNode);
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "searchNode")
    public Map<String, Object> searchNode(Map<String, String> data) throws Exception {
        List<NodeDto> resultList = this.nodeService.searchNode(data);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nodeList", resultList);
        return map;
    }

    @PUT
    @Produces({ "application/json" })
    @Path(value = "addNode")
    public Map<String, Object> addNode(NodeDto nodeDto) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            nodeService.addNode(nodeDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path(value = "updateNode")
    public Map<String, String> updateNode(NodeDto nodeDto) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        try {
            nodeService.updateNode(nodeDto);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
        }
        return result;
    }
}
