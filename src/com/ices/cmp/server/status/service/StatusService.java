package com.ices.cmp.server.status.service;

import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.status.dto.StatusDto;


public interface StatusService {
    public List<StatusDto> findAllStatus(Integer id) throws DtoException;
}
