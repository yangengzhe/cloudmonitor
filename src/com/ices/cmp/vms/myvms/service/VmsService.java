package com.ices.cmp.vms.myvms.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.vms.myvms.dto.VmsDto;


public interface VmsService {
    public void deleteVms(int[] deleteVms);

    public List<VmsDto> findAllVms(PagingBean pb) throws DtoException;

    public List<VmsDto> findByParam(Map<String, String> data) throws DtoException;

    public void addVms(VmsDto vmsDto) throws Exception;

    public void updateVms(VmsDto vmsDto) throws Exception;
    
    public List<VmsDto> findVmsByName(String name) throws DtoException;
    
    public VmsDto findVmsById(Integer id) throws DtoException;
}
