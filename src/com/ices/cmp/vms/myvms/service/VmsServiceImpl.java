package com.ices.cmp.vms.myvms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.common.enumitem.ServerState;
import com.ices.cmp.server.myserver.domain.Server;
import com.ices.cmp.vms.myvms.domain.Vms;
import com.ices.cmp.vms.myvms.dto.VmsDto;

@Service
public class VmsServiceImpl implements VmsService {

    @Override
    public void deleteVms(int[] deleteVms) {
        for (int i = 0; i < deleteVms.length; i++) {
            Vms vms = Vms.findById(deleteVms[i]);
            vms.delete();
        }
        
    }

    @Override
    public List<VmsDto> findAllVms(PagingBean pb) throws DtoException {
        List<VmsDto> resultList = new ArrayList<VmsDto>();
        List<Vms> result = Vms.findAllVms(pb);
        for (Vms vms : result) {
            VmsDto vmsDto = DtoHelper.build(VmsDto.class, vms);
            resultList.add(vmsDto);
        }
        return resultList;
    }

    @Override
    public List<VmsDto> findByParam(Map<String, String> data) throws DtoException {
        List<VmsDto> resultList = new ArrayList<VmsDto>();
        List<Vms> result = Vms.findByParam(data.get("vmsName"));
        for (Vms vms : result) {
            VmsDto vmsDto = DtoHelper.build(VmsDto.class, vms);
            resultList.add(vmsDto);
        }
        return resultList;
    }

    @Override
    public void addVms(VmsDto vmsDto) throws Exception {
        List<Vms> result = Vms.findByDuplicate(null,vmsDto.getCode());
        if (result != null && result.size() > 0) {
            throw new BusinessException("数据库中已经存在相同的服务器编码！");
        }
        Server server = Server.findByDuplicate(null, vmsDto.getServerCode()).get(0);
        Vms vms = DtoHelper.dismantle(Vms.class, vmsDto);
        vms.setState(ServerState.getMatchByName(vmsDto.getState()));
        vms.setServer(server);
        vms.insert();
        
    }

    @Override
    public void updateVms(VmsDto vmsDto) throws Exception {
        List<Vms> result = Vms.findByDuplicate(vmsDto.getId(),vmsDto.getCode());
        if (result != null && result.size() > 0) {
            System.out.println("出错了！" + vmsDto.getCode());
            throw new BusinessException("数据库中已经存在相同的服务器编码！");
        }
        Server server = Server.findByDuplicate(null, vmsDto.getServerCode()).get(0);
        Vms vms = Vms.findById(vmsDto.getId());
        vms.setState(ServerState.getMatchByName(vmsDto.getState()));
        vms.setServer(server);
        DtoHelper.dismantle(vms, vmsDto);
    }

    @Override
    public List<VmsDto> findVmsByName(String name) throws DtoException {
        List<VmsDto> resultList = new ArrayList<VmsDto>();
        List<Vms> result = Vms.findByParam(name);
        for (Vms vms : result) {
            VmsDto vmsDto = DtoHelper.build(VmsDto.class, vms);
            resultList.add(vmsDto);
        }
        return resultList;
    }

    @Override
    public VmsDto findVmsById(Integer id) throws DtoException {
        Vms vms = Vms.findById(id);
        VmsDto vmsDto = DtoHelper.build(VmsDto.class, vms);
        return vmsDto;
    }

}
