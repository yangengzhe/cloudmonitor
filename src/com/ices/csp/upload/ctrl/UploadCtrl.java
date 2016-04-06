package com.ices.csp.upload.ctrl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.cspframework.web.upload.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ices.csp.upload.service.UploadFileService;

@Path(value = "upload")
@Component
public class UploadCtrl {
	private static final String START = "-开始";
	private static final String END = "-结束";
	
	@Autowired
	private UploadFileService fileService;
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	/* 此处必须设置为TXT_HTML，否则ExtJS前台不能获得正确的返回 */
	@Produces("text/html;charset=utf-8")
	@Path(value = "uploadDoc")
	public String uploadDoc(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws Exception {
		UploadResponse uploadRst = new UploadResponse();
		String saveDir = "C://upload//doc";
		ObjectMapper ob = new ObjectMapper();
		
		uploadRst = this.fileService.uploadFile(request,saveDir);

		String resStr = ob.writeValueAsString(uploadRst);
		return resStr;
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	/* 此处必须设置为TXT_HTML，否则ExtJS前台不能获得正确的返回 */
	@Produces("text/html;charset=utf-8")
	@Path(value = "uploadCode")
	public String uploadCode(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws Exception {
		UploadResponse uploadRst = new UploadResponse();
		String saveDir = "C://upload//code";
		ObjectMapper ob = new ObjectMapper();
		
		uploadRst = this.fileService.uploadFile(request,saveDir);

		String resStr = ob.writeValueAsString(uploadRst);
		return resStr;
	}
}