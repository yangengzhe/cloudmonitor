package com.ices.cmp.vms.vmstatus.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.vms.vmstatus.domain.Vmstatus;
import com.ices.cmp.vms.vmstatus.dto.VmstatusDto;

@Service
public class VmstatusServiceImpl implements VmstatusService {

    @Override
    public List<VmstatusDto> findAllVmstatus(Integer id) throws DtoException {
        List<VmstatusDto> resultList = new ArrayList<VmstatusDto>();
        List<Vmstatus> result = Vmstatus.findAll(id);
        for (Vmstatus Route : result) {
            VmstatusDto RouteDto = DtoHelper.build(VmstatusDto.class, Route);
            RouteDto.setNet_download(RouteDto.getDownload());
            RouteDto.setNet_upload(RouteDto.getUpload());
            RouteDto.setMem(RouteDto.getMemory());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); 
            RouteDto.setDate(sdf.format(new Date(Long.valueOf(RouteDto.getTimestamp()+"000"))));
            resultList.add(RouteDto);
        }
        return resultList;
    }

}
