package com.ices.cmp.server.route.dto;

import org.cspframework.core.dto.annotation.DtoClass;
import org.cspframework.core.dto.annotation.DtoProperty;

import com.ices.cmp.server.route.domain.Route;

@DtoClass(entities = Route.class)
public class RouteDto {
    @DtoProperty(entityClass = Route.class)
    private Integer id;//服务器id
    
    @DtoProperty(entityClass=Route.class,entityProperty="node.id")
    private Integer nodeid;
    
    @DtoProperty(entityClass = Route.class)
    private String code;
    
    @DtoProperty(entityClass = Route.class)
    private String name;
    
    @DtoProperty(entityClass = Route.class)
    private String superroute;
    
    @DtoProperty(entityClass = Route.class)
    private String desc;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    


    
    public Integer getNodeid() {
        return nodeid;
    }


    
    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }


    
    public String getSuperroute() {
        return superroute;
    }


    
    public void setSuperroute(String superroute) {
        this.superroute = superroute;
    }


    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }


    
    public String getDesc() {
        return desc;
    }


    
    public void setDesc(String desc) {
        this.desc = desc;
    }

    

}
