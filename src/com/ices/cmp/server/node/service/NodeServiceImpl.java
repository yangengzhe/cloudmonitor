package com.ices.cmp.server.node.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.common.exception.BusinessException;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.springframework.stereotype.Service;

import com.ices.cmp.server.myserver.domain.Server;
import com.ices.cmp.server.node.domain.Node;
import com.ices.cmp.server.node.dto.NodeDto;





@Service
public class NodeServiceImpl implements NodeService{
	// 删除节点
	@Override
	public void deleteNode(int[] deleteNode) {
		for (int i = 0; i < deleteNode.length; i++) {
			Node node = Node.findById(deleteNode[i]);
			List<Server> serverList = Server.findServersByNodeId(deleteNode[i]);
			for (Server server : serverList) {
				server.setNode(null);
			}
			node.delete();
		}

	}
	// 查询所有数据表中已定义的节点
	@Override
	public List<NodeDto> findAllNode(PagingBean pb) throws DtoException {
		List<NodeDto> resultList = new ArrayList<NodeDto>();
		List<Node> result = Node.findAll(pb);
		for (Node node : result) {
			NodeDto nodeDto = DtoHelper.build(NodeDto.class, node);
			resultList.add(nodeDto);
		}
		return resultList;
	}
	// 查询符合条件的节点(根据节点名称)
	@Override
	public List<NodeDto> searchNode(Map<String, String> data) throws DtoException {
		List<NodeDto> resultList = new ArrayList<NodeDto>();
		List<Node> result = Node.findByName(data.get("nodeName"));
		for (Node node : result) {
			NodeDto nodeDto = DtoHelper.build(NodeDto.class, node);
			resultList.add(nodeDto);
		}
		return resultList;
	}
	// 增加新定义的节点
	@Override
	public void addNode(NodeDto nodeDto) throws Exception {
		List<Node> result = Node.findByDuplicate(null,nodeDto.getCode());
		if (result != null && result.size() > 0) {
			throw new BusinessException("数据库中已经存在相同的节点编码！");
		}
		Node node = DtoHelper.dismantle(Node.class, nodeDto);
		node.insert();
	}
	//更新修改节点信息
	@Override
	public void updateNode(NodeDto nodeDto) throws Exception {
		List<Node> result = Node.findByDuplicate(nodeDto.getId(),nodeDto.getCode());
		if (result != null && result.size() > 0) {
			System.out.println("出错了！" + nodeDto.getCode());
			throw new BusinessException("数据库中已经存在相同的节点编码！");
		}
		Node node = Node.findById(nodeDto.getId());
		DtoHelper.dismantle(node, nodeDto);
	}
}
