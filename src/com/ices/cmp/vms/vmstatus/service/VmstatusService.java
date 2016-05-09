package com.ices.cmp.vms.vmstatus.service;

import java.util.List;

import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.status.dto.StatusDto;
import com.ices.cmp.vms.vmstatus.dto.VmstatusDto;

public interface VmstatusService {
    public List<VmstatusDto> findAllVmstatus(Integer id) throws DtoException;
}
