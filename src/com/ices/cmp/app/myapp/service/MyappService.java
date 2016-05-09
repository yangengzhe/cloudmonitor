package com.ices.cmp.app.myapp.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.app.myapp.dto.MyappDto;


public interface MyappService {
    public void deleteMyapp(int[] deleteMyapp);

    public List<MyappDto> findAllMyapp(PagingBean pb) throws DtoException;

    public List<MyappDto> findByParam(Map<String, String> data) throws DtoException;

    public void addMyapp(MyappDto vmsDto) throws Exception;

    public void updateMyapp(MyappDto vmsDto) throws Exception;
    
    public List<MyappDto> findMyappByName(String name) throws DtoException;
    
    public MyappDto findMyappById(Integer id) throws DtoException;
}
