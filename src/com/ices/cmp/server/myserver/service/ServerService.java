package com.ices.cmp.server.myserver.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.myserver.dto.ServerDto;


public interface ServerService {
	public void deleteServer(int[] deleteServer);

	public List<ServerDto> findAllServer(PagingBean pb) throws DtoException;

	public List<ServerDto> findByParam(Map<String, String> data) throws DtoException;

	public void addServer(ServerDto serverDto) throws Exception;

	public void updateServer(ServerDto serverDto) throws Exception;
	
	public List<ServerDto> findServerBynodeName(String nodeName) throws DtoException;
	
	public ServerDto findServerById(Integer id) throws DtoException;
}
