package com.ices.cmp.menu.service;

import java.util.List;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.menu.dto.CmpMenuDto;
import com.ices.cmp.server.myserver.dto.ServerDto;
import com.ices.cmp.server.node.dto.NodeDto;

public interface CmpMenuService {
    public List<CmpMenuDto> findAllNode() throws DtoException;
    public List<CmpMenuDto> findServerBynodeId(Integer nodeId) throws DtoException;
    public List<CmpMenuDto> findVmsBynodeId(Integer nodeId) throws DtoException;
    public List<CmpMenuDto> findAppBynodeId(Integer nodeId) throws DtoException;
}
