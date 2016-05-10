package com.ices.cmp.app.evaluate.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.app.evaluate.domain.Alert;
import com.ices.cmp.app.evaluate.domain.Evaluate;
import com.ices.cmp.app.evaluate.dto.AlertDto;
import com.ices.cmp.app.evaluate.dto.EvaluateDto;
import com.ices.cmp.common.enumitem.AlertState;

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

    @Override
    public void deleteAlert(int[] deleteAlert) {
        for (int i = 0; i < deleteAlert.length; i++) {
            Alert alert = Alert.findById(deleteAlert[i]);
            alert.delete();
        }
    }

    @Override
    public List<AlertDto> findAllAlert(PagingBean pb) throws DtoException {
        List<AlertDto> resultList = new ArrayList<AlertDto>();
        List<Alert> result = Alert.findAllAlert(pb);
        for (Alert server : result) {
            AlertDto serverDto = DtoHelper.build(AlertDto.class, server);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            serverDto.setDate(sdf.format(new Date(Long.valueOf(serverDto.getTimestamp()+"000"))));
            resultList.add(serverDto);
        }
        return resultList;
    }

    @Override
    public void updateAlert(int[] signAlert) throws Exception {
        for (int i = 0; i < signAlert.length; i++) {
            Alert alert = Alert.findById(signAlert[i]);
            alert.setState(AlertState.YD);
            alert.update();
        }
    }

}
