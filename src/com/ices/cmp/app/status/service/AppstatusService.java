package com.ices.cmp.app.status.service;

import java.util.List;

import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.app.status.dto.AppstatusDto;

public interface AppstatusService {
    public List<AppstatusDto> findAllStatus(Integer id) throws DtoException;
}
