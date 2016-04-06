package com.ices.csp.cloudapp.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cspframework.common.PagingBean;
import org.cspframework.core.dto.exception.DtoException;
import org.cspframework.core.dto.util.DtoHelper;
import org.cspframework.manage.enterprise.domain.Enterprise;
import org.cspframework.manage.user.domain.LoginUser;
import org.cspframework.manage.user.domain.User;
import org.cspframework.utils.helper.LogHelper;
import org.cspframework.web.upload.MultipartRequestWrapper;
import org.cspframework.web.upload.UploadResponse;
import org.springframework.stereotype.Service;

import com.ices.csp.cloudapp.domain.CloudApp;
import com.ices.csp.cloudapp.dto.CloudAppDto;
import com.ices.csp.cloudapp.dto.CloudAppDto2;
import com.ices.csp.common.enumitem.cloudapp.CloudAppState;
import com.ices.csp.common.enumitem.cloudapp.CloudAppType;
import com.ices.csp.common.enumitem.cloudapp.WSDeploy;
import com.ices.csp.database.domain.Database;
import com.ices.csp.database.dto.DatabaseDto;
import com.ices.csp.software.domain.Software;
import com.ices.csp.webserver.domain.WebServer;
import com.ices.csp.webserver.dto.WebServerDto;

@Service
public class CloudAppServiceImpl implements CloudAppService{
	@Override
	public void changepw(Map<String, String> data) throws DtoException {
		String id = data.get("id");
		CloudApp cloudapp = CloudApp.findById(Integer.parseInt(id));
		cloudapp.setPassword(data.get("password"));
	}
	// 查询所有数据表中已定
	@Override
	public List<CloudAppDto2> findAllapp(PagingBean pb) throws DtoException {
		List<CloudAppDto2> resultList = new ArrayList<CloudAppDto2>();
		List<CloudApp> result = CloudApp.findAll(pb);
		for (CloudApp app : result) {
			CloudAppDto2 cloudAppDto = DtoHelper.build(CloudAppDto2.class, app);
			resultList.add(cloudAppDto);
		}
		return resultList;
	}
	@Override
	public List<CloudAppDto2> findAllmyapp(PagingBean pb) throws DtoException {
		List<CloudAppDto2> resultList = new ArrayList<CloudAppDto2>();
		List<CloudApp> result = CloudApp.findAllmyapp(pb);
		for (CloudApp app : result) {
			CloudAppDto2 cloudAppDto = DtoHelper.build(CloudAppDto2.class, app);
			resultList.add(cloudAppDto);
		}
		return resultList;
	}
	@Override
	public List<WebServerDto> findwebserver(Integer webappid) throws DtoException {
		List<WebServerDto> resultList = new ArrayList<WebServerDto>();
		Software web = CloudApp.findById(webappid).getWsType();
		List<WebServer> result = WebServer.findwebserver(web);
		for (WebServer webserver : result) {
			WebServerDto webServerDto = DtoHelper.build(WebServerDto.class, webserver);
			resultList.add(webServerDto);
		}
		return resultList;
	}
	@Override
	public List<DatabaseDto> finddbserver(Integer webappid) throws DtoException {
		List<DatabaseDto> resultList = new ArrayList<DatabaseDto>();
		Software web = CloudApp.findById(webappid).getDbType();
		List<Database> result = Database.finddbserver(web);
		for (Database dbserver : result) {
			DatabaseDto databaseDto = DtoHelper.build(DatabaseDto.class, dbserver);
			resultList.add(databaseDto);
		}
		return resultList;
	}
	@Override
	public List<CloudAppDto2> findByParam(Map<String, String> data) throws DtoException {
		List<CloudAppDto2> resultList = new ArrayList<CloudAppDto2>();
		List<CloudApp> result = CloudApp.findByParam(data.get("cloudappname"),data.get("formdate"),data.get("todate"));
		for (CloudApp app : result) {
			CloudAppDto2 cloudAppDto = DtoHelper.build(CloudAppDto2.class, app);
			resultList.add(cloudAppDto);
		}
		return resultList;
	}
	@Override
	public List<CloudAppDto2> searchmyapp(Map<String, String> data) throws DtoException {
		List<CloudAppDto2> resultList = new ArrayList<CloudAppDto2>();
		CloudAppState state = CloudAppState.getMatchByName(data.get("cloudappstate"));
		List<CloudApp> result = CloudApp.searchmyapp(data.get("cloudappname"),state);
		for (CloudApp app : result) {
			CloudAppDto2 cloudAppDto = DtoHelper.build(CloudAppDto2.class, app);
			resultList.add(cloudAppDto);
		}
		return resultList;
	}
	@Override
	public void saveConfig(Map<String, String> data) throws DtoException {
		String webappid = data.get("webappid");
		CloudApp cloudapp = CloudApp.findById(Integer.parseInt(webappid));
		if(data.get("webserverid")!=null&&data.get("webserverid")!=""){
			WebServer webserver = WebServer.query(Integer.parseInt(data.get("webserverid")));
			cloudapp.setWebServer(webserver);
		}
		if(data.get("dbserverid")!=null&&data.get("dbserverid")!=""){
			Database database = Database.query(Integer.parseInt(data.get("dbserverid")));
			cloudapp.setDatabase(database);
		}

		
		cloudapp.setUrl(data.get("url"));
		cloudapp.setDns(data.get("dns"));
		cloudapp.setAppIp(data.get("appIp"));
		cloudapp.setAppPort(Integer.parseInt(data.get("appPort")));
		
		cloudapp.setDbIp(data.get("dbIp"));
		cloudapp.setDbPort(Integer.parseInt(data.get("dbPort")));
		cloudapp.setPassword(data.get("password"));
		cloudapp.setUsername(data.get("username"));
	}
	@Override
	public void commitConfig(Integer webappid) throws DtoException {
		CloudApp cloudapp = CloudApp.findById(webappid);
		CloudAppState state = CloudAppState.configured;
		cloudapp.setState(state);
	}
	@Override
	public void addcloudapp(CloudAppDto cloudAppDto) throws Exception {
		CloudApp cloudApp = DtoHelper.dismantle(CloudApp.class, cloudAppDto);
		LoginUser user = User.getCurrentUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String datetime = sdf.format(date);
		String time = datetime.substring(10, 19);
		//String randomno = UUID.randomUUID().toString();

		String applydate = cloudAppDto.getRegisterTime().substring(0,10)+time;
		cloudApp.setRegisterTime(applydate);
		CloudAppState cloudAppState = CloudAppState.registered;
		cloudApp.setState(cloudAppState);

		cloudApp.setEnterprise(Enterprise.findById(user.getEnterpriseId()));
		cloudApp.setProvider(User.findById(user.getUserId()).getName());
		cloudApp.setType(CloudAppType.getMatchByName(cloudAppDto.getType()));
		Software software = Software.query(cloudAppDto.getWsType());
		cloudApp.setWsType(software);
		Software software2 = Software.query(cloudAppDto.getDbType());
		cloudApp.setDbType(software2);
		cloudApp.setWsdeploy(WSDeploy.getMatchByName(cloudAppDto.getWsdeploy()));
		cloudApp.setDbStore(WSDeploy.getMatchByName(cloudAppDto.getDbStore()));
		cloudApp.setBeginTime(cloudAppDto.getBeginTime().substring(0, 10)+" 00:00:00");
		cloudApp.setEndTime(cloudAppDto.getEndTime().substring(0, 10)+" 00:00:00");
		cloudApp.insert();
	}
	@Override
	public UploadResponse uploadFile(HttpServletRequest request,
			String saveDir, Integer permitedSize) throws Exception {
		File f = new File(saveDir);
		if (!f.exists())
			f.mkdirs();
		UploadResponse res = new UploadResponse();
		try {
			MultipartRequestWrapper multipartRequest = new MultipartRequestWrapper(
					request, saveDir, permitedSize, "UTF-8");
			File fileIn = multipartRequest.getFile("filePath");
			res.setSuccess(true);
			res.setResult(fileIn.getAbsolutePath());
		} catch (Exception e) {
			LogHelper.error(this.getClass(), "文件上传出错：" + e.getMessage());
			res.setSuccess(false);
			res.setResult("文件上传出错：" + e.getMessage());
		}
		return res;
	}
}
