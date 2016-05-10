package com.ices.cmp.app.evaluate.service;

import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.app.evaluate.dto.AlertDto;
import com.ices.cmp.app.evaluate.dto.EvaluateDto;

public interface EvaluateService {
    public List<EvaluateDto> findAllStatus(Integer id) throws DtoException;
    
    public void deleteAlert(int[] deleteAlert);

    public List<AlertDto> findAllAlert(PagingBean pb) throws DtoException;
    
    public void updateAlert(int[] signAlert) throws Exception;
}
