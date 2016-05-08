package com.ices.cmp.server.route.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.route.dto.RouteDto;


public interface RouteService {
    public void deleteRoute(int[] deleteRoute);

    public List<RouteDto> findAllRoute(PagingBean pb,int nodeid) throws DtoException;

    public List<RouteDto> searchRoute(Map<String, String> data) throws DtoException;

    public void addRoute(RouteDto RouteDto) throws Exception;

    public void updateRoute(RouteDto RouteDto) throws Exception;
}
