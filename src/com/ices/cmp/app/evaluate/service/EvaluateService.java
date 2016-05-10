package com.ices.cmp.app.evaluate.service;

import java.util.List;

import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.app.evaluate.dto.EvaluateDto;

public interface EvaluateService {
    public List<EvaluateDto> findAllStatus(Integer id) throws DtoException;
}
