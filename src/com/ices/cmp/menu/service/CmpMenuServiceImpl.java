package com.ices.cmp.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.app.myapp.domain.Myapp;
import com.ices.cmp.menu.dto.CmpMenuDto;
import com.ices.cmp.server.myserver.domain.Server;
import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.node.domain.Node;
import com.ices.cmp.server.node.dto.NodeDto;
import com.ices.cmp.vms.myvms.domain.Vms;
@Service
public class CmpMenuServiceImpl implements CmpMenuService {

    @Override
    public List<CmpMenuDto> findAllNode() throws DtoException {
        List<CmpMenuDto> resultList = new ArrayList<CmpMenuDto>();
        List<Node> result = Node.findAll();
        for (Node node : result) {
            CmpMenuDto menuDto = new CmpMenuDto();
            menuDto.setId(node.getId());
            menuDto.setLeaf(false);
            menuDto.setName(node.getName());
            menuDto.setServiceId(node.getId());
            resultList.add(menuDto);
        }
        return resultList;
    }

    @Override
    public List<CmpMenuDto> findServerBynodeId(Integer nodeId) throws DtoException {
        List<CmpMenuDto> resultList = new ArrayList<CmpMenuDto>();
        List<Server> result = Server.findServersByNodeId(nodeId);
        for (Server server : result) {
            CmpMenuDto menuDto = new CmpMenuDto();
            menuDto.setId(server.getId());
            menuDto.setLeaf(true);
            menuDto.setName(server.getIp());//用IP做名字
            menuDto.setServiceId(server.getId());
            resultList.add(menuDto);
        }
        return resultList;
    }

    @Override
    public List<CmpMenuDto> findVmsBynodeId(Integer nodeId) throws DtoException {
        List<CmpMenuDto> resultList = new ArrayList<CmpMenuDto>();
        List<Server> result = Server.findServersByNodeId(nodeId);
        for (Server server : result) {
            List<Vms> vmlist = Vms.findVmsByServerId(server.getId());
            for(Vms vms:vmlist){
                CmpMenuDto menuDto = new CmpMenuDto();
                menuDto.setId(vms.getId());
                menuDto.setLeaf(true);
                menuDto.setName(vms.getIp());//用IP做名字
                menuDto.setServiceId(vms.getId());
                resultList.add(menuDto);
            }
        }
        return resultList;
    }

    @Override
    public List<CmpMenuDto> findAppBynodeId(Integer nodeId) throws DtoException {
        List<CmpMenuDto> resultList = new ArrayList<CmpMenuDto>();
        List<Server> result = Server.findServersByNodeId(nodeId);
        for (Server server : result) {
            List<Vms> vmlist = Vms.findVmsByServerId(server.getId());
            for(Vms vms:vmlist){
                List<Myapp> myapp = Myapp.findByVmId(vms.getId());
                for(Myapp app:myapp){
                    CmpMenuDto menuDto = new CmpMenuDto();
                    menuDto.setId(app.getId());
                    menuDto.setLeaf(true);
                    menuDto.setName(app.getName());//用IP做名字
                    menuDto.setServiceId(app.getId());
                    resultList.add(menuDto);
                }
            }
        }
        return resultList;
    }
    
}
