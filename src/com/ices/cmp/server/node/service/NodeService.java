package com.ices.cmp.server.node.service;

import java.util.List;
import java.util.Map;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;

import com.ices.cmp.server.node.dto.NodeDto;






public interface NodeService {

	public void deleteNode(int[] deleteNode);

	public List<NodeDto> findAllNode(PagingBean pb) throws DtoException;

	public List<NodeDto> searchNode(Map<String, String> data) throws DtoException;

	public void addNode(NodeDto nodeDto) throws Exception;

	public void updateNode(NodeDto nodeDto) throws Exception;

}
