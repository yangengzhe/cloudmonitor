package com.ices.cmp.app.evaluate.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.app.evaluate.domain.Evaluate;
import com.ices.cmp.app.evaluate.dto.EvaluateDto;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Override
    public List<EvaluateDto> findAllStatus(Integer id) throws DtoException {
        List<EvaluateDto> resultList = new ArrayList<EvaluateDto>();
        List<Evaluate> result = Evaluate.findAll(id);
        for (Evaluate Route : result) {
            EvaluateDto RouteDto = DtoHelper.build(EvaluateDto.class, Route);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); 
            RouteDto.setDate(sdf.format(new Date(Long.valueOf(RouteDto.getTimestamp()+"000"))));
            resultList.add(RouteDto);
        }
        return resultList;
    }

}
