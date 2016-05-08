package com.ices.cmp.server.usage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.server.usage.domain.Usage;
import com.ices.cmp.server.usage.dto.UsageDto;
@Service
public class UsageServiceImpl implements UsageService {

    @Override
    public List<UsageDto> findAllUsage(Integer id) throws DtoException {
        List<UsageDto> resultList = new ArrayList<UsageDto>();
        List<Usage> result = Usage.findAll(id);
        for (Usage Route : result) {
            UsageDto RouteDto = DtoHelper.build(UsageDto.class, Route);
            resultList.add(RouteDto);
        }
        return resultList;
    }

}
