package com.ices.cmp.app.myapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.app.myapp.domain.Myapp;
import com.ices.cmp.app.myapp.dto.MyappDto;
import com.ices.cmp.common.enumitem.ServerState;
import com.ices.cmp.vms.myvms.domain.Vms;

@Service
public class MyappServiceImpl implements MyappService {

    @Override
    public void deleteMyapp(int[] deleteMyapp) {
        for (int i = 0; i < deleteMyapp.length; i++) {
            Myapp myapp = Myapp.findById(deleteMyapp[i]);
            myapp.delete();
        }
        
    }

    @Override
    public List<MyappDto> findAllMyapp(PagingBean pb) throws DtoException {
        List<MyappDto> resultList = new ArrayList<MyappDto>();
        List<Myapp> result = Myapp.findAllMyapp(pb);
        for (Myapp myapp : result) {
            MyappDto myappDto = DtoHelper.build(MyappDto.class, myapp);
            resultList.add(myappDto);
        }
        return resultList;
    }

    @Override
    public List<MyappDto> findByParam(Map<String, String> data) throws DtoException {
        List<MyappDto> resultList = new ArrayList<MyappDto>();
        List<Myapp> result = Myapp.findByParam(data.get("appName"));
        for (Myapp myapp : result) {
            MyappDto myappDto = DtoHelper.build(MyappDto.class, myapp);
            resultList.add(myappDto);
        }
        return resultList;
    }

    @Override
    public void addMyapp(MyappDto myappDto) throws Exception {
        List<Myapp> result = Myapp.findByDuplicate(null,myappDto.getCode());
        if (result != null && result.size() > 0) {
            throw new BusinessException("数据库中已经存在相同的服务器编码！");
        }
        Vms vms = Vms.findByDuplicate(null, myappDto.getVmCode()).get(0);
        Myapp myapp = DtoHelper.dismantle(Myapp.class, myappDto);
        myapp.setState(ServerState.getMatchByName(myappDto.getState()));
        myapp.setVms(vms);
        myapp.insert();
        
    }

    @Override
    public void updateMyapp(MyappDto myappDto) throws Exception {
        List<Myapp> result = Myapp.findByDuplicate(myappDto.getId(),myappDto.getCode());
        if (result != null && result.size() > 0) {
            System.out.println("出错了！" + myappDto.getCode());
            throw new BusinessException("数据库中已经存在相同的服务器编码！");
        }
        Vms vms = Vms.findByDuplicate(null, myappDto.getVmCode()).get(0);
        Myapp myapp = Myapp.findById(myappDto.getId());
        myapp.setState(ServerState.getMatchByName(myappDto.getState()));
        myapp.setVms(vms);
        DtoHelper.dismantle(myapp, myappDto);
    }

    @Override
    public List<MyappDto> findMyappByName(String name) throws DtoException {
        List<MyappDto> resultList = new ArrayList<MyappDto>();
        List<Myapp> result = Myapp.findByParam(name);
        for (Myapp myapp : result) {
            MyappDto myappDto = DtoHelper.build(MyappDto.class, myapp);
            resultList.add(myappDto);
        }
        return resultList;
    }

    @Override
    public MyappDto findMyappById(Integer id) throws DtoException {
        Myapp myapp = Myapp.findById(id);
        MyappDto myappDto = DtoHelper.build(MyappDto.class, myapp);
        return myappDto;
    }
}
