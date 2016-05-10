package com.ices.cmp.app.status.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.app.status.domain.Appstatus;
import com.ices.cmp.app.status.dto.AppstatusDto;

@Service
public class AppstatusServiceImpl implements AppstatusService {

    @Override
    public List<AppstatusDto> findAllStatus(Integer id) throws DtoException {
        List<AppstatusDto> resultList = new ArrayList<AppstatusDto>();
        List<Appstatus> result = Appstatus.findAll(id);
        for (Appstatus Route : result) {
            AppstatusDto RouteDto = DtoHelper.build(AppstatusDto.class, Route);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); 
            RouteDto.setDate(sdf.format(new Date(Long.valueOf(RouteDto.getTimestamp()+"000"))));
            resultList.add(RouteDto);
        }
        return resultList;
    }

}
