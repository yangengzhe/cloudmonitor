package com.ices.cmp.topology.vms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.cspframework.core.dto.exception.DtoException;
import org.springframework.stereotype.Service;

import com.ices.cmp.server.myserver.domain.Server;
import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.route.domain.Route;
import com.ices.cmp.topology.server.dto.TopoServerDto;
import com.ices.cmp.vms.myvms.domain.Vms;
import com.ices.cmp.vms.myvms.dto.VmsDto;
import com.ices.cmp.vms.myvms.service.VmsService;
@Service
public class TopoVmsServiceImpl implements TopoVmsService {

    @Override
    public List<TopoServerDto> getServerTopo(VmsDto vms) throws DtoException {
        Server server = Server.findByDuplicate(null, vms.getServerCode()).get(0);
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
        
        Server father_server = null;
        int temp_father = -1;
        for (Server temp : list) {
            TopoServerDto topo = new TopoServerDto();
            topo.setFather(father);
            topo.setId(id);
            topo.setName(temp.getIp());
            if(temp.getId().equals(server.getId())){
                topo.setStatus(0);
                father_server=temp;
                temp_father = id;
            }
            else
                topo.setStatus(1);
            topo.setType("server");
            id++;
            resultlist.add(topo);
        }
        //反差添加vm
        List<Vms> vmslist= Vms.findVmsByServerId(father_server.getId());
        for(Vms vm:vmslist){
            TopoServerDto topo = new TopoServerDto();
            topo.setFather(temp_father);
            topo.setId(id);
            topo.setName(vm.getName());
            if(vm.getId().equals(vms.getId()))
                topo.setStatus(0);
            else
                topo.setStatus(1);
            topo.setType("vm");
            id++;
            resultlist.add(topo);
        }
        
        return resultlist;
    }

}
