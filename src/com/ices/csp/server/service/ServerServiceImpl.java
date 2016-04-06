package com.ices.csp.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.csp.node.domain.Node;
import com.ices.csp.server.domain.Server;
import com.ices.csp.server.dto.ServerDto;
import com.ices.csp.common.enumitem.IsVirtual;
import com.ices.csp.common.enumitem.ServerCategory;
import com.ices.csp.common.enumitem.ServerState;
import com.ices.csp.common.enumitem.ServerType;

@Service
public class ServerServiceImpl implements ServerService{
	
	@Override
	public List<ServerDto> findServerBynodeName(String nodeName) throws DtoException {
		List<ServerDto> resultList = new ArrayList<ServerDto>();
		List<Server> result = Server.findServerBynodeName(nodeName);
		for (Server server : result) {
			ServerDto serverDto = DtoHelper.build(ServerDto.class, server);
			resultList.add(serverDto);
		}
		return resultList;
	}
	// 删除服务器
	@Override
	public void deleteServer(int[] deleteServer) {
		for (int i = 0; i < deleteServer.length; i++) {
			Server server = Server.findById(deleteServer[i]);
			server.delete();
		}

	}
	// 查询所有数据表中已定义的服务器
	@Override
	public List<ServerDto> findAllServer(PagingBean pb) throws DtoException {
		List<ServerDto> resultList = new ArrayList<ServerDto>();
		List<Server> result = Server.findAllServer(pb);
		for (Server server : result) {
			ServerDto serverDto = DtoHelper.build(ServerDto.class, server);
			resultList.add(serverDto);
		}
		return resultList;
	}
	// 增加新定义的服务器
	@Override
	public void addServer(ServerDto serverDto) throws Exception {
		List<Server> result = Server.findByDuplicate(null,serverDto.getCode());
		if (result != null && result.size() > 0) {
			throw new BusinessException("数据库中已经存在相同的服务器编码！");
		}
		Node node = Node.findByName(serverDto.getNodeName()).get(0);
		Server server = DtoHelper.dismantle(Server.class, serverDto);
		server.setType(ServerType.getMatchByName(serverDto.getType()));
		server.setState(ServerState.getMatchByName(serverDto.getState()));
		server.setCategory(ServerCategory.getMatchByName(serverDto.getCategory()));
		server.setFlag(IsVirtual.getMatchByName(serverDto.getFlag()));
		server.setNode(node);
		server.insert();
	}
	//更新修改服务器信息
	@Override
	public void updateServer(ServerDto serverDto) throws Exception {
		List<Server> result = Server.findByDuplicate(serverDto.getId(),serverDto.getCode());
		if (result != null && result.size() > 0) {
			System.out.println("出错了！" + serverDto.getCode());
			throw new BusinessException("数据库中已经存在相同的服务器编码！");
		}
		Node node = Node.findByName(serverDto.getNodeName()).get(0);
		Server server = Server.findById(serverDto.getId());
		server.setType(ServerType.getMatchByName(serverDto.getType()));
		server.setState(ServerState.getMatchByName(serverDto.getState()));
		server.setCategory(ServerCategory.getMatchByName(serverDto.getCategory()));
		server.setFlag(IsVirtual.getMatchByName(serverDto.getFlag()));
		server.setNode(node);
		DtoHelper.dismantle(server, serverDto);
	}
	// 查询符合条件的节点(根据节点名称)
	@Override
	public List<ServerDto> findByParam(Map<String, String> data) throws DtoException {
		List<ServerDto> resultList = new ArrayList<ServerDto>();
		List<Server> result = Server.findByParam(data.get("nodeName"),data.get("serverName"));
		for (Server server : result) {
			ServerDto serverDto = DtoHelper.build(ServerDto.class, server);
			resultList.add(serverDto);
		}
		return resultList;
	}
}
