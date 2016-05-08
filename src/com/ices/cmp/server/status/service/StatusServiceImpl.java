package com.ices.cmp.server.status.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.server.status.domain.Status;
import com.ices.cmp.server.status.dto.StatusDto;

@Service
public class StatusServiceImpl implements StatusService {

    @Override
    public List<StatusDto> findAllStatus(Integer id) throws DtoException {
        List<StatusDto> resultList = new ArrayList<StatusDto>();
        List<Status> result = Status.findAll(id);
        for (Status Route : result) {
            StatusDto RouteDto = DtoHelper.build(StatusDto.class, Route);
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
