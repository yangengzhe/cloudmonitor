package com.ices.cmp.topology.vms.service;

import java.util.List;

import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.topology.server.dto.TopoServerDto;
import com.ices.cmp.vms.myvms.dto.VmsDto;

public interface TopoVmsService {
    public List<TopoServerDto> getServerTopo(VmsDto vms) throws DtoException;
}
