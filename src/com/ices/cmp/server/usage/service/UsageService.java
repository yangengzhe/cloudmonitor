package com.ices.cmp.server.usage.service;

import java.util.List;

import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.usage.dto.UsageDto;

public interface UsageService {
    public List<UsageDto> findAllUsage(Integer id) throws DtoException;
}
