package com.ices.cmp.topology.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.cspframework.core.dto.exception.DtoException;
import org.springframework.stereotype.Service;

import com.ices.cmp.server.myserver.domain.Server;
import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.route.domain.Route;
import com.ices.cmp.server.route.dto.RouteDto;
import com.ices.cmp.topology.server.dto.TopoServerDto;
@Service
public class TopoServerImpl implements TopoServer {

    @Override
    public List<TopoServerDto> getServerTopo(ServerDto server) throws DtoException {
        String superroute = server.getSuperroute();
        List<Server> list =Server.findBySuperroute(superroute);
        List<TopoServerDto> resultlist = new ArrayList<TopoServerDto>();
        Stack<Route> stack = new Stack<Route>();
        
        Route route = Route.findByCode(superroute);
        stack.push(route);
        while(!route.getSuperroute().equals("0")){
            route = Route.findByCode(route.getSuperroute());
            stack.push(route);
        }
        int father = -1;
        int id = 0;
        while(!stack.isEmpty()){
            route = stack.pop();
            TopoServerDto topo = new TopoServerDto();
            topo.setFather(father);
            topo.setId(id);
            topo.setName(route.getName());
            topo.setStatus(1);
            topo.setType("route");
            id++;
            father++;
            resultlist.add(topo);
          //此处待修改，反查同一级的路由
            List<Route> routelist = Route.findBySuperroute(route.getCode());
            for (Route route2 : routelist) {
//                if(route2.getId().equals(route.getId()) || father == -1)
//                    continue;
                topo = new TopoServerDto();
                topo.setFather(father);
                topo.setId(id);
                topo.setName(route2.getName());
                topo.setStatus(1);
                topo.setType("route");
                id++;
                resultlist.add(topo);
            }
        }
        
//        int id = father+1;
        for (Server temp : list) {
            TopoServerDto topo = new TopoServerDto();
            topo.setFather(father);
            topo.setId(id);
            topo.setName(temp.getIp());
            if(temp.getId().equals(server.getId()))
                topo.setStatus(0);
            else
                topo.setStatus(1);
            topo.setType("server");
            id++;
            resultlist.add(topo);
        }
        return resultlist;
    }

}
