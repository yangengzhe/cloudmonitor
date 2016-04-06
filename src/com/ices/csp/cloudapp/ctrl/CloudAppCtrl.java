/**
 * 
 */
package com.ices.csp.cloudapp.ctrl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.cspframework.common.PagingBean;
import org.cspframework.web.upload.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.csp.cloudapp.domain.CloudApp;
import com.ices.csp.cloudapp.dto.CloudAppDto;
import com.ices.csp.cloudapp.dto.CloudAppDto2;
import com.ices.csp.cloudapp.service.CloudAppService;
import com.ices.csp.database.dto.DatabaseDto;
import com.ices.csp.webserver.dto.WebServerDto;

/**
 * @author MFC ZZY
 *
 */
@Path(value = "register")
@Component
public class CloudAppCtrl {
	/*---------------------类成员定义-------------------------*/
	@Autowired
	private CloudAppService cloudAppService;

	/*-----------------------业务逻辑-------------------------*/
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "changepw")
	public Map<String, Object> changepw(Map<String, String> data) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			cloudAppService.changepw(data);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllwebapp")
	public Map<String, Object> findAllapp(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<CloudAppDto2> resultList = this.cloudAppService.findAllapp(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findAllmyapp")
	public Map<String, Object> findAllmyapp(@QueryParam(value = "paging") PagingBean pb) throws Exception {
		List<CloudAppDto2> resultList = this.cloudAppService.findAllmyapp(pb);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appList", resultList);
		map.put("pagingBean",pb);
		return map;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "findwebserver")
	public Map<String, Object> findwebserver(@QueryParam(value = "webappid") Integer webappid) throws Exception {
		List<WebServerDto> resultList = this.cloudAppService.findwebserver(webappid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("webserverList", resultList);
		return map;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "finddbserver")
	public Map<String, Object> finddbserver(@QueryParam(value = "webappid") Integer webappid) throws Exception {
		List<DatabaseDto> resultList = this.cloudAppService.finddbserver(webappid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbserverList", resultList);
		return map;
	}
	@PUT
	@Produces({ "application/json" })
	@Path(value = "addcloudapp")
	public Map<String, Object> addcloudapp(CloudAppDto cloudAppDto) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			cloudAppService.addcloudapp(cloudAppDto);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchcloudapp")
	public Map<String, Object> searchcloudapp(Map<String, String> data) throws Exception {
		List<CloudAppDto2> resultList = this.cloudAppService.findByParam(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appList", resultList);
		return map;
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "searchmyapp")
	public Map<String, Object> searchmyapp(Map<String, String> data) throws Exception {
		List<CloudAppDto2> resultList = this.cloudAppService.searchmyapp(data);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appList", resultList);
		return map;
	}
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "saveConfig")
	public Map<String, Object> saveConfig(Map<String, String> data) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			cloudAppService.saveConfig(data);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "commitConfig")
	public Map<String, Object> commitConfig(@QueryParam(value = "webappId") Integer webappId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			cloudAppService.commitConfig(webappId);
			result.put("status", "success");
		} catch (Exception e) {
			result.put("status", "error");
		}
		return result;
	}
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	/* 此处必须设置为TXT_HTML，否则ExtJS前台不能获得正确的返回 */
	@Produces("text/html;charset=utf-8")
	@Path(value = "uploadwebcode")
	public String uploadwebcode(@Context HttpServletRequest request,
			@Context HttpServletResponse response,@QueryParam(value = "webappid") Integer webappid) throws Exception {
		final int permitedSize = 5*1024 * 1024 * 1024;//大小
		ObjectMapper ob = new ObjectMapper();
//		String fileName = null;
//		String destPath = null;
		UploadResponse uploadRst = new UploadResponse();
		try {
			 uploadRst = this.cloudAppService.uploadFile(request,
					org.cspframework.common.Global.UPLOAD_FOLDER + "webserver"
							+ File.separator, permitedSize);
				String rawFilePath = uploadRst.getResult().toString();
				// 设置最终有效图片的保存路径
				//uploadRst.setResult(rawFilePath);
				CloudApp.findById(webappid).setWsCode(rawFilePath);
//				if (rawFilePath != null) {
//					// 获取文件名
//					int index = rawFilePath.lastIndexOf(File.separator);
//					if (index != -1) {
//						fileName = rawFilePath.substring(index + 1);
//						// 构造目标存储路径
//						destPath = destDir + imageName;
//					}
//				}
		} catch (Exception e) {
			uploadRst.setSuccess(false);
			uploadRst.setResult(e.getMessage());
		}
		String resStr = ob.writeValueAsString(uploadRst);
		return resStr;
	}
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	/* 此处必须设置为TXT_HTML，否则ExtJS前台不能获得正确的返回 */
	@Produces("text/html;charset=utf-8")
	@Path(value = "uploaddocument")
	public String uploaddocument(@Context HttpServletRequest request,
			@Context HttpServletResponse response,@QueryParam(value = "webappid") Integer webappid) throws Exception {
		final int permitedSize = 1*1024 * 1024 * 1024;//大小
		ObjectMapper ob = new ObjectMapper();
		UploadResponse uploadRst = new UploadResponse();
		try {
			 uploadRst = this.cloudAppService.uploadFile(request,
					org.cspframework.common.Global.UPLOAD_FOLDER + "document"
							+ File.separator, permitedSize);
				String rawFilePath = uploadRst.getResult().toString();
				CloudApp.findById(webappid).setDocument(rawFilePath);
		} catch (Exception e) {
			uploadRst.setSuccess(false);
			uploadRst.setResult(e.getMessage());
		}
		String resStr = ob.writeValueAsString(uploadRst);
		return resStr;
	}
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	/* 此处必须设置为TXT_HTML，否则ExtJS前台不能获得正确的返回 */
	@Produces("text/html;charset=utf-8")
	@Path(value = "uploadpicture")
	public String uploadpicture(@Context HttpServletRequest request,
			@Context HttpServletResponse response,@QueryParam(value = "webappid") Integer webappid) throws Exception {
		final int permitedSize = 1*1024 * 1024 * 1024;//大小
		ObjectMapper ob = new ObjectMapper();
		UploadResponse uploadRst = new UploadResponse();
		try {
			 uploadRst = this.cloudAppService.uploadFile(request,
					org.cspframework.common.Global.UPLOAD_FOLDER + "picture"
							+ File.separator, permitedSize);
				String rawFilePath = uploadRst.getResult().toString();
				CloudApp.findById(webappid).setPicture(rawFilePath);
		} catch (Exception e) {
			uploadRst.setSuccess(false);
			uploadRst.setResult(e.getMessage());
		}
		String resStr = ob.writeValueAsString(uploadRst);
		return resStr;
	}
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	/* 此处必须设置为TXT_HTML，否则ExtJS前台不能获得正确的返回 */
	@Produces("text/html;charset=utf-8")
	@Path(value = "uploaddbfile")
	public String uploaddbfile(@Context HttpServletRequest request,
			@Context HttpServletResponse response,@QueryParam(value = "webappid") Integer webappid) throws Exception {
		final int permitedSize = 1*1024 * 1024 * 1024;//大小
		ObjectMapper ob = new ObjectMapper();
		UploadResponse uploadRst = new UploadResponse();
		try {
			 uploadRst = this.cloudAppService.uploadFile(request,
					org.cspframework.common.Global.UPLOAD_FOLDER + "dbfile"
							+ File.separator, permitedSize);
				String rawFilePath = uploadRst.getResult().toString();
				CloudApp.findById(webappid).setDbFile(rawFilePath);
		} catch (Exception e) {
			uploadRst.setSuccess(false);
			uploadRst.setResult(e.getMessage());
		}
		String resStr = ob.writeValueAsString(uploadRst);
		return resStr;
	}
}
