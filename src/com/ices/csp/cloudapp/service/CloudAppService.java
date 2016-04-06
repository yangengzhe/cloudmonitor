package com.ices.csp.cloudapp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.web.upload.UploadResponse;

import com.ices.csp.cloudapp.dto.CloudAppDto;
import com.ices.csp.cloudapp.dto.CloudAppDto2;
import com.ices.csp.database.dto.DatabaseDto;
import com.ices.csp.webserver.dto.WebServerDto;

public interface CloudAppService {
	public void changepw(Map<String, String> data) throws DtoException;
	public void addcloudapp(CloudAppDto cloudAppDto) throws Exception;
	
	public List<CloudAppDto2> findAllapp(PagingBean pb) throws DtoException;
	public List<CloudAppDto2> findAllmyapp(PagingBean pb) throws DtoException;
	
	public List<CloudAppDto2> findByParam(Map<String, String> data) throws DtoException;
	public List<CloudAppDto2> searchmyapp(Map<String, String> data) throws DtoException;
	public List<WebServerDto> findwebserver(Integer webappid) throws DtoException;
	
	public List<DatabaseDto> finddbserver(Integer webappid) throws DtoException;
	
	public void saveConfig(Map<String, String> data) throws DtoException;
	
	public void commitConfig(Integer webappid) throws DtoException;
	
	
	public abstract UploadResponse uploadFile(HttpServletRequest request, String saveDir, Integer permitedSize) throws Exception;
}
