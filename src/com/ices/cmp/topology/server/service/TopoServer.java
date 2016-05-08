package com.ices.cmp.topology.server.service;

import java.util.List;

import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.usage.dto.UsageDto;
import com.ices.cmp.topology.server.dto.TopoServerDto;

public interface TopoServer {
    public List<TopoServerDto> getServerTopo(ServerDto server) throws DtoException;
}
