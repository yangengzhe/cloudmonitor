package com.ices.csp.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.cspframework.common.Global;
import org.springframework.stereotype.Component;

@Path("download")
@Component
public class DownLoadCtrl {
	  @Path("file")
	  @GET
	  @Produces({"application/*;charset=utf-8"})
	    public void downloadLocal(@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
		    String root = Global.WEBROOT_PATH;
		    String rootPath = request.getParameter("rootPath");
		    String filePath = request.getParameter("filePath");
		    String fileName = request.getParameter("fileName");
		    if (rootPath != null) {
		      if (rootPath.equals("WEB-INFO")) {
		        root = Global.WEB_INFO_PATH;
		      }
		      else if (rootPath.equals("CLASSES")) {
		        root = Global.WEB_CLASSES_PATH;
		      }
		    }
		    FileInputStream inputStream = new FileInputStream("C:/1.txt");
		    OutputStream out = response.getOutputStream();
		    response.setCharacterEncoding("UTF-8");
		    response.addHeader("Content-Disposition", 
		      "attachment; filename=" + fileName);
		    response.setContentType("application/*");
		    response.addHeader("Cache-Control", "no-cache");

		    int b = 0;
		    byte[] buffer = new byte[512];
		    while (b != -1) {
		      b = inputStream.read(buffer);
		      if (b != -1) {
		        out.write(buffer, 0, b);
		      }
		    }
		    inputStream.close();
		    out.close();
		    out.flush();
		  }
//	  public void getExcel(@Context HttpServletRequest request, @Context HttpServletResponse response)
//	    throws Exception
//	  {}
}
