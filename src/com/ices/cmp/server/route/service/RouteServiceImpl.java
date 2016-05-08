package com.ices.cmp.server.route.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.server.node.domain.Node;
import com.ices.cmp.server.route.domain.Route;
import com.ices.cmp.server.route.dto.RouteDto;

@Service
public class RouteServiceImpl implements RouteService {

    @Override
    public void deleteRoute(int[] deleteRoute) {
        // TODO Auto-generated method stub
        for (int i = 0; i < deleteRoute.length; i++) {
            Route route = Route.findById(deleteRoute[i]);
            route.delete();
        }
    }

    @Override
    public List<RouteDto> findAllRoute(PagingBean pb,int nodeid) throws DtoException {
        List<RouteDto> resultList = new ArrayList<RouteDto>();
        List<Route> result = Route.findAll(pb,nodeid);
        for (Route Route : result) {
            RouteDto RouteDto = DtoHelper.build(RouteDto.class, Route);
            resultList.add(RouteDto);
        }
        return resultList;
    }

    @Override
    public List<RouteDto> searchRoute(Map<String, String> data) throws DtoException {
        List<RouteDto> resultList = new ArrayList<RouteDto>();
        List<Route> result = Route.findByName(data.get("RouteName"));
        for (Route Route : result) {
            RouteDto RouteDto = DtoHelper.build(RouteDto.class, Route);
            resultList.add(RouteDto);
        }
        return resultList;
    }

    @Override
    public void addRoute(RouteDto RouteDto) throws Exception {
        List<Route> result = Route.findByDuplicate(null,RouteDto.getCode());
        if (result != null && result.size() > 0) {
            throw new BusinessException("数据库中已经存在相同的节点编码！");
        }
        Node node = Node.findById(RouteDto.getNodeid());
        Route Route = DtoHelper.dismantle(Route.class, RouteDto);
        Route.setNode(node);
        Route.insert();
        
    }

    @Override
    public void updateRoute(RouteDto RouteDto) throws Exception {
        List<Route> result = Route.findByDuplicate(RouteDto.getId(),RouteDto.getCode());
        if (result != null && result.size() > 0) {
            System.out.println("出错了！" + RouteDto.getCode());
            throw new BusinessException("数据库中已经存在相同的节点编码！");
        }
        Node node = Node.findById(RouteDto.getNodeid());
        Route route = Route.findById(RouteDto.getId());
        DtoHelper.dismantle(route, RouteDto);
        route.setNode(node);
    }

}
